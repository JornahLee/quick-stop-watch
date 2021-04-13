package com.jornah.stopwatch.easy;

import com.jornah.stopwatch.Util;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

// cglib实现动态代理
public class StopWatchAop<T> implements MethodInterceptor {
    private T target;//需要代理的目标对象
    private HashSet<String> functionSet;
    private final StopWatch stopWatch = new StopWatch();
    // 简单做个缓存，避免每次都重新生成代理对象
    // 使用弱引用，避免无法GC导致OOM
    private static WeakHashMap<Object, Object> aopObjects = new WeakHashMap<>();

    // 不提供构造方法，使用静态方法(getTimeWatchProxy)获取增强后的对象
    private StopWatchAop() {
    }

    //重写拦截方法
    @Override
    public Object intercept(Object obj, Method method, Object[] arr, MethodProxy proxy) throws Throwable {
        // 不需要增强, 暂时只支持根据方法名字进行增强， 暂时没必要根据方法签名来
        String methodName = method.getName();
        if (!functionSet.contains(methodName)) {
            return method.invoke(target, arr);
        }
        //前置增强
        stopWatch.reset();
        stopWatch.start();
        Object invoke = method.invoke(target, arr);//方法执行，参数：target 目标对象 arr参数数组
        //后置增强
        stopWatch.stop();
        String spends = Util.getFormatTime(stopWatch.getTime(TimeUnit.NANOSECONDS));

        String className = method.getDeclaringClass().getName();
        System.out.println(String.format("%s #%s ( spend: %s )", className, method, spends));
        return invoke;
    }

    //定义获取代理对象方法

    /**
     * 静态方法获取代理对象
     * @param objectTarget 需要增强的对象
     * @param functionNames 对象中需要增强的方法
     * @param <T> 泛型
     * @return 被增强的对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T getTimeWatchProxy(T objectTarget, String[] functionNames) {
        if (aopObjects.containsKey(objectTarget)) {
            return (T) aopObjects.get(objectTarget);
        }
        StopWatchAop<T> objectStopWatchAop = new StopWatchAop<>();
        objectStopWatchAop.functionSet = Objects.isNull(functionNames) ? new HashSet<>() : new HashSet<>(Arrays.asList(functionNames));
        //为目标对象target赋值
        objectStopWatchAop.target = objectTarget;
        Enhancer enhancer = new Enhancer();
        //设置父类,因为Cglib是针对指定的类生成一个子类，所以需要指定父类
        enhancer.setSuperclass(objectTarget.getClass());
        enhancer.setCallback(objectStopWatchAop);// 设置回调
        Object enhanceObj = enhancer.create();
        aopObjects.put(objectTarget, enhanceObj);
        return (T) enhanceObj;
    }
}