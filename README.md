# cy-ruoyi
SpringCloud Alibaba2.1 + Dubbo + MybatisPlus + Mysql + Redis + RocketMQ

---
## 中间件
* 注册中心：Nacos
* 配置中心：Nacos
* 限流熔断：Sentinel
* 消息队列：RocketMQ
* 分布式事务：Seata
* 分布式调用链：SkyWalking
* 分库分表：Mycat
* 分布式任务调度：XXL-Job
* 日志收集：ELK
* 容器：Docker

---

## 目录结构

 ```
cy-ruoyi
 ├──docs //文件
 |  ├──poms //相关依赖
 |  ├──server //服务包
 ├──ruoyi-common //通用包
 |  ├──ruoyi-common-core //核心框架包
 |  ├──ruoyi-common-redis //redis工具包
 |  ├──ruoyi-common-log //日志工具包
 |  ├──ruoyi-common-alipay // 支付宝（尚未）
 |  ├──ruoyi-common-utils // 通用工具包
 |  ├──ruoyi-common-swagger // 通用Swagger工具包
 |  ├──ruoyi-common-auth // 授权工具包
 |  ├──ruoyi-common-mail // 邮件工具包（尚未）
├──ruoyi-user //用户
 |  ├──ruoyi-user-application //用户启动
 |  ├──ruoyi-user-api //用户API
 |  ├──ruoyi-user-impl //用户实现
 |  ├──sql //用户sql
├──ruoyi-order //订单
 |  ├──ruoyi-order-application //订单启动
 |  ├──ruoyi-order-api //订单API
 |  ├──ruoyi-order-impl //订单实现
 |  ├──sql //订单sql
 ├──ruoyi-product // 商品（尚未）
 |  ├──ruoyi-order-application //商品启动
 |  ├──ruoyi-order-api //商品API
 |  ├──ruoyi-order-impl //商品实现
 |  ├──sql //商品sql
 ├──ruoyi-pay //支付（尚未）
 |  ├──ruoyi-pay-application //支付启动
 |  ├──ruoyi-pay-api //支付API
 |  ├──ruoyi-pay-impl //支付实现
 |  ├──sql //支付sql
 ├──ruoyi-stock //库存（尚未）
 |  ├──ruoyi-stock-application //库存启动
 |  ├──ruoyi-stock-api //库存API
 |  ├──ruoyi-stock-impl //库存实现
 |  ├──sql //库存sql
 ├──ruoyi-logistics //物流（尚未）
 |  ├──ruoyi-logistics-application //物流启动
 |  ├──ruoyi-logistics-api //物流API
 |  ├──ruoyi-logistics-impl //物流实现
 |  ├──sql //物流sql
 ├──ruoyi-tool //工具
 |  ├──ruoyi-gen //代码生成
 |  ├──ruoyi-auth // 授权鉴权
 |  ├──ruoyi-file //文件系统
 |  ├──ruoyi-data // 数据收集（尚未）
 |  ├──ruoyi-system // 系统监控（尚未）
 ├──ruoyi-ant --前端 使用ant design框架
 ```


