# DDD
    DDD架构中，能明显看出越外层的代码越稳定，越内层的代码演进越快，真正体现了领域“驱动”的核心思想。
    DDD不是一个什么特殊的架构，而是任何传统代码经过合理的重构之后最终一定会抵达的终点。DDD的架构能够有效的解决传统架构中的问题：
       *高可维护性：当外部依赖变更时，内部代码只用变更跟外部对接的模块，其他业务逻辑不变。
       *高可扩展性：做新功能时，绝大部分的代码都能复用，仅需要增加核心业务逻辑即可。
       *高可测试性：每个拆分出来的模块都符合单一性原则，绝大部分不依赖框架，可以快速的单元测试，做到100%覆盖。
       *代码结构清晰：通过POM module可以解决模块间的依赖关系， 所有外接模块都可以单独独立成Jar包被复用。当团队形成规范后，可以快速的定位到相关代码。

##三层架构：
    表现层（Interfaces）
    业务逻辑层（Business）
    持久层（Persistence）
##四层架构：
    表现层（Interfaces）
    应用层（Application）
    领域层（Domain）
    基础设施层（Infrastructure）

#【eglpt 采用4层架构 DDD版本 】

## com-platform-api 对外服务层(展现层/协议转换) 最顶层
    通常叫【interfaces/web/controller/adapter】因未来前后端分离是主流，后端只负责提供接，因此这里叫【api】，
    该层包含与其他系统进行交互的接口与通信设施，在多数应用里，该层可能提供包括Web Services、RMI或Rest等在内的一种或多种通信接口。
    【* HTTP和RPC接口，返回值为Result，捕捉所有异常】
## com-platform-client 应用API（对外暴漏的服务）
    如果不需要对外暴漏服务，可以和【application】 层合并（就是从application抽出的对外接口）
    服务（Service） 负责协调和委派，并不实现业务逻辑
    数据传输对象 dto（DataTransfer Object） 把内部的领域对与外部隔离
    exception 对外异常 ......
    视图展示数据 vo 返回前端的对象
    表面/门面（Facade）： 为远程客户端提供粗粒度的调用接口，他的作用就是将一个用户请求委派给一个或多个service进行处理(非必须) 
    说明: 对于rpc调用的服务，该层直接打包发送给调用方
## com-platform-application  应用层
    服务（Service） 负责协调和委派，并不实现业务逻辑
    数据传输对象 DTO（DataTransfer Object） 把内部的领域对与外部隔离
    转配（Assembler） 实现DTO与领域对象之间的互相转换，数据交互，几乎总是与DTO一起出现
    VO 视图层展示数据
    Converter  数据转换
    表面/门面（Facade）： 为远程客户端提供粗粒度的调用接口，他的作用就是将一个用户请求委派给一个或多个service进行处理(非必须)
## com-platform-domain   领域模型层（DDD的核心层 几乎全部的业务逻辑都会在该层实现）
    实体（Entity ）：具有唯一标识的对象 除了拥有数据之外，同时拥有行为。Entity和数据库储存格式无关，不需要有必然的联系（相关文献 https://blog.csdn.net/Taobaojishu/article/details/101444324）
    工厂(factories) :创建复杂对象，影藏创建细节
    Domain Event（领域事件）
    仓储（Repository）:提供查找和持久化对象的方法,  [有的地方也叫gateway这里区分微服务网关不这样叫]
    ablility： 一些无法归到实体对象或值对象上，本质是一些操作，而非事务
    Domain模块仅依赖Types模块，也是纯 POJO 。
    聚合/聚合根（aggregates,aggregate roots）：  聚合是指一组具有内聚关系的相关对象的集合，每个聚合都有一个 root 和 boundary
    防腐层（ACL） 有效的隔离外部依赖和内部逻辑，无论外部如何变更，内部代码可以尽可能的保持不变。
    中间件   
    说明: 对应各个服务内部通用的对象打包该组件 共用
##com-platform-infrastructure  基础实施层（最底层）
    向其他层提供通用的技术能力(比如工具类,第三方库类支持,常用基本配置,数据访问底层实现) 
    common      基础类库
    converter   数据转换
    domain      数据库对象
    mapper      数据库映射
    仓储（Repository）:提供查找和持久化对象的方法实现,   [有的地方也叫gateway这里区分微服务网关不这样叫]
##com-platform-dp 域原语 (RPC调用的时候才有效)
    Domain Primitives因为是无状态的逻辑，可以对外暴露，所以经常被包含在对外的API接口中，需要单独成为模块。该模块不依赖任何类库，纯 POJO 。
    DP可以是业务域的最小组成部分、也可以构建复杂组合 （有不可变的特性）
    exception
    使用DP的另一个好处就是代码遵循了 DRY 原则和单一性原则，如果未来需要修改 xxDP 的校验逻辑，
     只需要在一个文件里修改即可，所有使用到了 xxDP 的地方都会生效。
## Domain Primitive 的定义(摘抄自 阿里巴巴淘系技术团队官网博客 https://blog.csdn.net/Taobaojishu/article/details/100425428)
    Domain Primitive 是一个在特定领域里，拥有精准定义的、可自我验证的、拥有行为的 Value Object，一切需要改变状态的代码都不属于 DP 的范畴 。
    1.DP是一个传统意义上的Value Object，拥有Immutable（不变的）的特性
    2.DP是一个完整的概念整体，拥有精准定义
    3.DP使用业务域中的原生语言
    4.DP可以是业务域的最小组成部分、也可以构建复杂组合

    什么情况下应该用 Domain Primitive
    常见的 DP 的使用场景包括：
    
    1.有格式限制的 String：比如Name，PhoneNumber，OrderNumber，ZipCode，Address等
    
    2.有限制的Integer：比如OrderId（>0），Percentage（0-100%），Quantity（>=0）等
    
    3.可枚举的 int ：比如 Status（一般不用Enum因为反序列化问题）
    
    4.Double 或 BigDecimal：一般用到的 Double 或 BigDecimal 都是有业务含义的，比如 Temperature、Money、Amount、ExchangeRate、Rating 等
    
    5.复杂的数据结构：比如 Map<String, List<Integer>> 等，尽量能把 Map 的所有操作包装掉，仅暴露必要行为



##com-platform-common 域原语
    通用枚举、状态吗、异常、工具类 （各微服务之前通用的基础数据）
##com-platform-start  服务部署[不属于DDD]
    Start模块是SpringBoot的启动类。
    单元测试模块    

## 针对此项目的设计，api <—— application <—— infrastructure <—— domain
    infrastructure 进行所有跟数据库有关的操作，并将数据XXXDao 转换成XXXEntity 返回

