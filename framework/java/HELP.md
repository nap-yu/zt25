# zt25-framework（for java）
<p style="text-indent:2em;font-size: 16px">
基于 最新版 Spring Cloud 生态的一套轻量灵活、简洁的开发框架，开发上屏蔽底层细节开发者无需关心底层细节，直接使用开发业务即可。
</p>

## 开发须知:
- 基础: java-17
- 框架: Spring-cloud()、Spring-boot(3+)、mybatis-plus(3.5+)

## 模块
## zt25-core (框架核心模块)
其中包括框架中个模块的常量、领域对象定、枚举、异常、基类的定义及工具类的实现.

功能:
+ [x] 异常
    - [x] 基类
    - [x] 异常工厂
    - [x] 系统异常类
    - [x] 持久化异常类
    - [x] web异常类
+ [x] 枚举基类
    - [x] 基类
    - [x] 异常相关枚举
    - [x] 持久化相关枚举
    - [x] 状态相关枚举
    - [x] web相关枚举
+ [x] 对象(各种O)
    - [x] 持久化相关
    - [x] web相关
    - [x] 持久化相关枚举
    - [x] 状态相关枚举
    - [x] web相关枚举
+ [x] 常量
    - [x] web相关常量
+ [x] 工具
    - [x] 加解密接口
    - [x] base64加解密实现

## zt25-annotation (注解定义)
各模块相关注解的定义

## zt25-aop
框架aop模块

## zt25-converter
类型转换模块

## zt25-web
框架web模块

功能:
+ [x] controller基类
    - [x] 返回封装
    - [x] 获取请求方ip
    - [x] 获取请求方设备信息
+ [x] 拦截器加载
    - [x] 默认拦截
    - [x] 自定义拦截加载
+ [x] 出入参加解密
+ [x] 跨域的处理
+ [x] 默认拦截器实现
    - [x] 请求上下文的处理
    - [x] request及header的处理
    - [x] 对请求客户端信息的获取

## zt25-middle

#### zt25-redis
redis模块

#### zt25-config-center
配置中心模块

#### zt25-event
事件模块

#### zt25-gateway
网关模块

## zt25-persistence

#### zt25-define
持久化定义模块

#### zt25-mongo
mongodb模块

#### zt25-orm
基于mybatis-plus封装的orm模块

## zt25-spring-boot-start
框架启动器

## examples
示例程序模块

## 其他
运行时模板需要实现的方法用类名作为包,方法名为类名.这样是否可以在生成代码做代码检查,删除(添加废弃的注解,保留原先的实现)无用的逻辑实现