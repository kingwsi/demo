#### 穿透、雪崩、击穿
- 穿透：访问一个并不存在的key 可用布隆过滤器解决此问题（不存在的一定不存在，存在的可能不存在）
- 击穿：热点key失效，大量请求落库，可设置不过期，或提前一段时间定时更新，或者查询key时加锁，数据不存在去数据库查询并更新缓存，有其他查询请求阻塞起来
- 雪崩：大量缓存失效，或者redis宕机，有效期均匀分布，避免设置一样的；数据预热；redis集群
#### Redis分布式锁
- 实现方式