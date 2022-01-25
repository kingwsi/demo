#### mysql主从复制
###### 常用命令
- 查看slave状态 show slave status
- 停止主从复制 stop slave
- 重新配置 reset master
###### mycat读写分离类型（balance）
- balance=0 不开启
- balance=1 （常用）双主双从下Master2/Slave1/Slave2都参与读
- balance=2 再所有机器上随机分发
- balance=3 （常用）所有读操作在只读主机操作

##### 垂直拆分 - 分库
先分别创建数据库，在schema.xml中配置表在哪个库， 在通过mycat建表

##### 水平拆分 - 分表
可根据某字段按规则分到不同表中，
例如orders表，可按用户ID取模后划分到不同表

关联表，需手动配置映射关系，如orders_details中的order_id

公共表 如字典表，很多地方都要关联字典表查询
##### 常用分片规则
- 取模 通过对指定字段取模后决定落在哪个库
- 枚举 通过配置文件决定数据落在哪个库
- 范围约定 指定范围 如订单id 0-100=0
- 按日期 

##### 全局序列 ID重复问题
- 本地文件 不推荐 万一宕机 备机无法读取到
- 时间戳 不推荐18位ID太长  
- 数据库存储 推荐 每次mycat需要时会冲数据库批量获取一部分，避免每次都查询

##### mycat高可用方案
HAProxy Keepalived