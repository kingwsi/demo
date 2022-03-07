package com.demo.atomic;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * description:  原子引用包装类 <br>
 * date: 2022/1/25 13:09 <br>
 * author: ws <br>
 */
class User{
    int age;
    String name;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
public class DemoAtomicReference {
    public static void main(String[] args) {
        User u1 = new User(12, "u1");
        User u2 = new User(15, "u2");
        AtomicReference<User> reference = new AtomicReference<>();
        reference.set(u1);
        // 第一次更新
        System.out.println(reference.compareAndSet(u1, u2));
        // 第二次更新
        System.out.println(reference.compareAndSet(u2, u1));

        User u3 = new User(19, "u3");
        User u4 = new User(18, "u4");
        int initial = 1;
        AtomicStampedReference<User> stampedReference = new AtomicStampedReference<>(u3, initial);
        // 修改值
        System.out.println(stampedReference.compareAndSet(u3, u4, stampedReference.getStamp(), stampedReference.getStamp() + 1)+"\t"+ stampedReference.getStamp());
        // 改回去
        System.out.println(stampedReference.compareAndSet(u4, u3, stampedReference.getStamp(), stampedReference.getStamp() + 1)+"\t"+ stampedReference.getStamp());
        // 失败
        System.out.println(stampedReference.compareAndSet(u3, u4, initial, stampedReference.getStamp() + 1)+"\t"+ stampedReference.getStamp());

    }
}
