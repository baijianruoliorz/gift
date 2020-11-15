package com.wizz.gift.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 需要登录才能进行的操作
 * @author 郭树耸
 * @version 1.0
 * @date 2020/2/2 19:59
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserLogin {
    boolean required() default true;
}
