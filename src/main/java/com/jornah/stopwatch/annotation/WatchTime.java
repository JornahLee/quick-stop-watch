package com.jornah.stopwatch.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
//当注解未指定Target值时，则此注解可以用于任何元素之上
public @interface WatchTime {
}
