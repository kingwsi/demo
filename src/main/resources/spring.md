##### Base
###### Bean作用域 
1. com.demo.singleton 单例 默认
2. prototype 原型 每次调用getBean时实例化一个bean
3. request 每次请求实例化一个bean
4. session 一次会话中共享一个bean
###### 事务
- 事务传播行为：
    - 默认使用

##### 循环引用如何结局
三级缓存
实际上是三个容器，分别存储 Bean，Bean半成品，和代理对象Bean