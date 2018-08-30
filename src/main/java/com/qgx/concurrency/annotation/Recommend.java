package com.qgx.concurrency.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 *@Author: goxcheer
 *@Date:22:18 2018/8/30
 *@email:604721660@qq.com
 *@decription:自定义建议的类写法标识注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface Recommend {

    String value() default "";
}
