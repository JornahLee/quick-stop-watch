# 方法执行时间统计 
实现：使用commons-lang3 工具包下的 StopWatch + aop(cglib)

两种使用方法在不同情况：
1. 无spring容器：参考 `com.jornah.stopwatch.easy.Tester.main`
2. 有spring容器：基于spring-aop实现，直接在需要计时的方法上加注解`@WatchTime`，参考`com.jornah.stopwatch.XXXServiceTest`


# 总结：
1. 复习aop基本概念，切面，织入，切入点(通过注解切入)，通知，增强
2. 熟悉涉及jdk的api, `NumberFormat(数字显示格式), DecimalFormat(小数显示格式)`
3. 注解 https://blog.csdn.net/javazejian/article/details/71860633


# TODO
封装一个maven依赖，放到中央仓库
或者自定义一个maven私服，maven或者gradle依赖的时候，配置上自己私服地址