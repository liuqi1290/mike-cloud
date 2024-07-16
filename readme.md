## 项目说明

本项目的使用 `Spring-cloud-alibaba` 搭建的，有使用到 nacos，如果你想要将该项目运行起来，则需要启动本地的 `nacos`。

创建命名空间：

启动本地 `nacos`，新增命名空间

![在这里插入图片描述](https://img-blog.csdnimg.cn/233d59d214484caebc435ac70d82eeeb.png)

新增命名空间成功之后可以在 `配置管理` 中的 `配置列表` 中看到，选中，然后添加配置：

![在这里插入图片描述](https://img-blog.csdnimg.cn/f3ea2fa8a0a94dad90531037da6e2afe.png)

新建配置：

![在这里插入图片描述](https://img-blog.csdnimg.cn/ff4a0a897b0b4b5f9e6318199a98baa5.png)

各服务 nacos 的配置如下：

![在这里插入图片描述](https://img-blog.csdnimg.cn/a05bd7544a354b8fb6edff98d28bf13d.png)

mike-gateway 服务 -> nacos 上 `mike-gateway-local.yml` 配置：

```yml
server:
  port: 7000
logging:
  level:
    com.alibaba.nacos: error
spring:
  application:
    name: mike-gateway
  main:
    allow-bean-definition-overriding: true
  cloud:
    gateway:
      discovery:
        locator:
          # 让本服务从nacos上发现其他的服务
          enabled: true
      routes:
        # 网关路由配置
        - id: mike_user
          uri: lb://mike-server-user
          predicates:
            - Path=/user/**
          filters:
            - StripPrefix=1

        - id: mike_system
          uri: lb://mike-server-system
          predicates:
            - Path=/system/**
          filters:
            - StripPrefix=1  
```

mike-system 服务 -> nacos 上 `mike-server-system-local.yml` 配置：

```yml
server:
  port: 7001

spring:
  application:
    name: mike-server-system
```

mike-user 服务 -> nacos 上 `mike-server-user-local.yml` 配置：

```yml
server:
  port: 7002

spring:
  application:
    name: mike-server-user
```# mike-cloud
