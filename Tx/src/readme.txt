1.创建数据源  （是否采用连接池 c3p0 ,dbcp ,druid....）

2.创建事务管理器  (Spring 先创建所有的@Bean  然后再运行)

3.在配置类加入注解

4.在业务层加入事务注解
 @Transactional  // 传播机制 Propogation  隔离级别 Isolation  ReadOnly 只读  rollbackfor 为什么异常回滚
                // Timeout 事务超时时间

 ================================
 1. global , local transaction
    global ->JTA
    local ->结合aop实现的事务处理
 2. 事务的特性
 A :atomic 原子性
 C  : consistent 一致性
 I :  Isolation 隔离性
 D : Duraction 持久性
 3. 数据库的隔离策略及出现的问题
                    丢失更新   脏读   不可重复读  幻读
 read uncommitted     v       x         x      x
 read committed       v       v         x      x
 repeatal             v       v         v      x
 serialisable         v       v         v      v

 x锁 ：排它锁 s锁：共享锁
4. 为什么有这么多的IOC注解？ @Service将异常打包为Spring的异常