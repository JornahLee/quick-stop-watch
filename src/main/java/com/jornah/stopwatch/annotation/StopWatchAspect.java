package com.jornah.stopwatch.annotation;

import com.jornah.stopwatch.Util;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

//切面类
@Aspect
@Component
public class StopWatchAspect {

    // 注解方式设置切入点
    @Pointcut("@annotation(com.jornah.stopwatch.annotation.WatchTime)")
    public void myMethod() {
    }

    // @Before("myMethod()")
    // @After("myMethod()")
    // @AfterReturning("myMethod()")
    // @AfterThrowing("myMethod()")
    // public void before(JoinPoint joinPoint) {
        // System.out.println("meth od before");
    // }


    @Around("myMethod()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object ret = joinPoint.proceed(joinPoint.getArgs());
        stopWatch.stop();
        String spendStr;
        long spend = stopWatch.getTime(TimeUnit.NANOSECONDS);
        spendStr = Util.getFormatTime(spend);
        String className = joinPoint.getTarget().getClass().getName();
        System.out.println(String.format("%s # %s | spend: %s ", className, joinPoint.getSignature().toString(), spendStr));
        return ret;
    }

}