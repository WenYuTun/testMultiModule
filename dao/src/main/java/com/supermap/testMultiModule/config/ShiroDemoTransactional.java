package com.supermap.testMultiModule.config;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 连接shiro_demo数据源的自定义事务注解类
 * @author wenyutun
 *
 * @Target
 * 定义注解修饰的目标
 *
 * @Retention
 * 定义注解的生命周期，分为以下三种：
 * 源码级别
 * SOURCE
 * 编译期级别
 * CLASS
 * 运行期级别
 * RUNTIME
*/
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Transactional("transactionManager3")
public @interface ShiroDemoTransactional {
    Propagation propagation() default Propagation.REQUIRED;
}
