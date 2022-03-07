package com.demo.sync;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * description:  <br>
 * date: 2022/3/7 13:08 <br>
 * author: ws <br>
 */
public class TestCompletableFuture {
    public static void main(String[] args) {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("runAsync-01-" + Thread.currentThread().getName());
            return "runAsync-01";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("runAsync-02-" + Thread.currentThread().getName());
            return "runAsync-02";
        });
        // future1和future2 执行不分前后

        // 等待future1和future2执行完毕后执行
        String result = future1.thenCombine(future2, (s1, s2) -> {
            System.out.println("runAsync1执行结果--"+s1);
            System.out.println("runAsync2执行结果--"+s2);
            System.out.println("thenCombine-" + Thread.currentThread().getName());
            return "thenCombine 执行完成";
        }).join();


        System.out.println(result);
    }
}
