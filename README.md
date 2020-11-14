# proj_agent_codeEnhance
在JVM中使用Agent技术实现高效快捷的代码注入
## 依赖
##### 1.源码
    1.Javassist  
    2.Groovy
    3.Gson
    4.proj_agent
##### 2.运行
    1.messagebox弹框(目前仅实现Windows系统)
## 实现功能
    1.使用Groovy编写注入的代码，对项目代码零侵入，大部分代码更改保存后可立即生效
    2.提供友好的Javassist操纵能力
    3.支持json语法(支持linux系统)、Groovy注解来定义具体规则
    4.提供模板代码生成
## 原理
    在运行时通过Instrumentation在字节码中添加代理实现的代码，使用GroovyClassLoader加载待注入的代码，并执行回调，将返回的结果替换到原来的地方
## 使用场景
    1.在Spring中注入动态Bean实例，调用任意方法(Groovy提供的动态能力)、任意属性
    2.对方法调用甚至每条语句进行控制(Javassist提供的修改能力)