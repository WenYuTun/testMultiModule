package com.supermap.testMultiModule.config;

import java.lang.annotation.*;

/**
 * @author wenyutun
 * @description: 自定义请求记录注解
 * @date: 2019/8/29
 * @version: 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface WebLog {

    /**
     * 接口描述，默认为空
     */
    String description() default "";
}
