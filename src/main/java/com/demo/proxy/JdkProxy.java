package com.demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * description:  <br>
 * date: 2022/1/11 15:13 <br>
 * author: ws <br>
 */
public class JdkProxy implements InvocationHandler {
    public Object bean;

    public JdkProxy(Object bean) {
        this.bean = bean;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("com.demo.proxy ...");
        return method.invoke(bean, args);
    }


    public static void main(String[] args) {
        Person person = new PersonImpl();

        JdkProxy jdkProxy = new JdkProxy(person);
        // 创建代理对象
        Person proxyPerson = (Person)Proxy.newProxyInstance(jdkProxy.getClass().getClassLoader(), new Class[]{Person.class}, jdkProxy);
        proxyPerson.sayHello();
        System.out.println(proxyPerson);
    }
}

interface Person {
    void sayHello();
}

class PersonImpl implements Person {

    @Override
    public void sayHello() {
        System.out.println("hello!");
    }
}
