#### ACID
[详细说明](https://zhuanlan.zhihu.com/p/117476959)
- 原子性 Atomicity undolog 实现该特性。
- 一致性 Consistency
- 隔离性 Isolation MVCC 最大的优点是读不加锁（隐藏列
- 持久性 Durability InnoDB 通过 redo log 重做日志保证了事务的持久性。
#### 事务隔离级别
- 读未提交 一个事务内可读到其他未提交的事务内的修改
- 读提交 就是不可重复读，一个事务内多次查询结果可能不同
- 可重复读 一个事务内，同样的查询调教结果相同
- 串行化 一个事务需要等另一个事务执行完才能执行
#### sql执行顺序
FORM - WHERE - GROUP BY - HAVING - ORDER BY - LIMIT - OFFSET
#### 索引
联合索引 最左匹配原则 [详细说明](https://zhuanlan.zhihu.com/p/115778804)
##### 1.SELECT * FROM table WHERE a = 1 and b = 2 and c = 3;
(a,b,c) (c,b,a) (b,c,a)

##### 2.SELECT * FROM table WHERE a > 1 and b = 2;
(a,b) 范围查询联合索引失效 将b排在最前面

##### 3.SELECT * FROM table WHERE a > 1 and b = 2 and c > 3;
(b,a) (b,c) 都行

##### 4.SELECT * FROM table WHERE a = 1 ORDER BY b;
(a,b)

#### MVCC机制

#### 原子性保证：使用undolog