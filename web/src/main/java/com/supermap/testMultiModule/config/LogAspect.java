package com.supermap.testMultiModule.config;

import com.supermap.testMultiModule.ov.ResponseJsonData;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: WenYutun
 * @date: 21:55 2018/12/6
 * @description: 实现Web层的日志切面
 */
@Aspect
@Component
public class LogAspect {

    /**
     * 日志
     */
    private final static Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 计时器
     */
    private static ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 配置切面
     * ~第一个 *代表任意修饰符及任意返回值.
     * ~第二个 *定义在controller包或者子包
     * ~第三个 *任意方法
     * ~ ..匹配任意数量的参数.
     *
     * 切点为标注了@WebLog注解的方法上
     */
    @Pointcut("@annotation(com.supermap.testMultiModule.config.WebLog)")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        //获取请求开始时间
        startTime.set(System.currentTimeMillis());
        //通过RequestContextHolder获取ServletRequestAttributes对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获取请求
        HttpServletRequest request = attributes.getRequest();
        //url
        LOG.info("url = {}", request.getRequestURL());
        //http请求方式
        LOG.info("method = {}", request.getMethod());
        //ip
        LOG.info("ip = {}", request.getRemoteAddr());
        //类方法
        LOG.info("class_method = {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        //方法参数
        LOG.info("args = {}", joinPoint.getArgs());
    }

    @AfterReturning(returning = "responseJsonData", pointcut = "webLog()")
    public void doAfterReturning(ResponseJsonData responseJsonData) {
        LOG.info("response = {}", responseJsonData.getData().toString());
        //输出响应时间
        LOG.info("本次请求共耗时：：：" + (System.currentTimeMillis() - startTime.get()) + "ms");

    }

}
