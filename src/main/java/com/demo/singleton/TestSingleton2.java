package com.demo.singleton;

/**
 * description:  单例模式<br>
 * https://www.runoob.com/design-pattern/singleton-pattern.html
 * <p>
 * Spring中Bean作用域：
 * 1. com.demo.singleton 单例
 * 2. prototype 原型 每次调用getBean时实例化一个bean
 * 3. request 每次请求实例化一个bean
 * 4. session 一次会话中共享一个bean
 * <p>
 * date: 2022/1/21 09:56 <br>
 * author: ws <br>
 */
public class TestSingleton2 {

    //创建一个静态对象
    public static TestSingleton2 instance = null;

    //让构造函数为 private，这样该类就不会被实例化
    private TestSingleton2() {
        System.out.println(Thread.currentThread().getName() + "创建对象");
    }

    /**
     * DLC Double Check Lock 双端检锁
     * @return
     */
    public static TestSingleton2 getInstance() {
        if (instance == null) {
            synchronized (TestSingleton2.class) {
                if (instance == null) {
                    instance = new TestSingleton2();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                TestSingleton2.getInstance();
            }, "Thread-" + i).start();
        }
    }
}
