# zt25-framework（for java）
<p style="text-indent:2em;font-size: 16px">
基于 最新版 Spring Cloud 生态的一套轻量灵活、简洁的开发框架，开发上屏蔽底层细节开发者无需关心底层细节，直接使用开发业务即可。
</p>

## 一. 开发须知:
- 基础: java-17
- 框架: Spring-cloud()、Spring-boot(3+)、mybatis-plus(3.5+)

## 二. 模块
## 1. zt25-core (框架核心模块)
其中包括框架中个模块的常量、领域对象定、枚举、异常、基类的定义及工具类的实现.

功能:
+ [x] 异常
    - [x] 基类
    - [x] 异常工厂
    - [x] 系统异常类
+ [x] 枚举基类
    - [x] 基类
    - [x] 异常相关枚举
    - [x] 状态相关枚举
+ [x] 对象(各种O)
    - [x] 状态相关枚举
+ [x] 工具
    - [x] 加解密接口
    - [x] base64加解密实现
    - [X] hutool

功能:

+ [x] web
    - [x] 自定义拦截器
    - [x] 入参解密
    - [x] 出参加密

## 2. zt25-aop
框架aop模块

功能:
+ [x] 自定义aop处理

## 3. zt25-converter
类型转换模块

功能：
+ [x] 通用类型转换
    - [x] 对象间转换
    - [x] 对象集合转换
+ [x] 数据处理
    - [x] 数据脱敏
    - [x] 数据加密
    - [x] 自定义数据处理

## 4. zt25-web
框架web模块

#### 4.1 zt25-web-domain
定义web相关域对象

+ [x] 异常
    - [x] web异常类
+ [x] 枚举基类
    - [x] web相关枚举
+ [x] 对象(各种O)
    - [x] web相关
    - [x] web相关枚举
+ [x] 常量
    - [x] web相关常量

#### 4.2 zt25-web-base
基于spring-boot-web的模块

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
+ [x] 异常返回处理
+ [X] 统一出参处理
+ [x] 参数校验
+ [x] controller方法接收多参数（实验性功能）
+ [x] 将springboot的json转换器改成fastjson2

## 5. zt25-middle

#### 5.1 zt25-redis
redis模块

#### 5.2 zt25-config-center
配置中心模块

#### 5.3 zt25-event
事件模块

#### 5.4 zt25-gateway
网关模块

#### 5.4 zt25-分布式事务
分布式事务

## 6. zt25-persistence

#### 6.1 zt25-persistence-domain
持久化定义模块

功能：
+ [x] 注解
    - [x] 默认值
    - [x] 主键类型
+ [x] 领域对象
    - [x] 查询条件对象
    - [x] 持久化对象基类
+ [x] 枚举
    - [x] 异常
    - [x] 主键类型
    - [x] 数据库sql操作类型‘
    - [x] 排序
    - [x] 查询条件
    - [x] 查询条件连接符
+ [x] 异常对象
+ [x] 仓储接口
+ [x] 自定义主键生成器接口

#### 6.2 zt25-persistence-mongo
mongodb模块

#### 6.3 zt25-persistence-orm
基于mybatis-plus封装的orm模块。postgres、mysql、mariadb测试。默认数据库改成postgres？

功能：
+ [x] mybatis-plus插件
    - [x] 分页插件
    - [x] 防全表更新及删除插件
    - [x] 乐观锁插件（需实体中定义version）
    - [x] 使用sql批处理语句进行批量添加及更新
+ [x] 主键生成器
    - [x] 接口定义
    - [x] objectid
    - [x] uuid
    - [x] 短uuid
    - [x] nano
    - [x] 雪花（Long）
    - [x] 雪花（String）
+ [x] 拦截器
    - [x] 主键填充
    - [x] 默认值填充
    - [x] 自定义默认值填充策略
 + [ ] 开启mybatis三级缓存（redis）
 + [ ] 多数据源支持（待确定）
 + [ ] 自定数据源配置初始化

## 7. zt25-spring-boot-start
框架启动器

#### 7.1 zt25-web-spring-boot-start
基于spring-boot-web的项目启动器。后续会将配置文件放入到此模块中

功能：
+ [ ] 配置文件
+ [ ] 配置文件信息加密（需设计器）

## 三. examples
示例程序模块

## 四. 其他
1. 运行时模板需要实现的方法用类名作为包,方法名为类名.这样是否可以在生成代码做代码检查,删除(添加废弃的注解,保留原先的实现)无用的逻辑实现
2. 设计器要做主键类型判断，防止填充主键时出现类型错误
