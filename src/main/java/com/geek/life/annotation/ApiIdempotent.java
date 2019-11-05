package com.geek.life.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 在需要保证接口幂等性 的Controller的方法上使用此注解
 * @Author: 屈艳锋
 * @Date: 2019/6/10 15:53
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiIdempotent {
}
