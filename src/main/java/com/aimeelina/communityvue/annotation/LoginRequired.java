package com.aimeelina.communityvue.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//指定用在什么东西上
@Retention(RetentionPolicy.RUNTIME)//指定生效的时间
public @interface LoginRequired {
}
