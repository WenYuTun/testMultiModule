package com.supermap.testMultiModule.config;

import java.lang.annotation.*;

/**
 * @author wenyutun
 * @description: 自定义防止请求重复提交注解
 * @date: 2019/8/29
 * @version: 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ReSubmit {

    /*
    延时时间，在延时多久后可以再次提交
     */
    int delaySeconds() default 10;
}
