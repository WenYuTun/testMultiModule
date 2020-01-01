package com.supermap.testMultiModule.config;

import com.supermap.testMultiModule.ov.ResponseJsonData;
import com.supermap.testMultiModule.util.ResubmitLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author wenyutun
 * @description: 数据重复提交校验切面
 * @date: 2019/8/29
 * @version: 1.0
 */
@Component
@Aspect
public class ResubmitDataAspect {

    private final static Object PRESENT = new Object();

    @Around("@annotation(com.supermap.testMultiModule.config.ReSubmit)")
    public Object handleResubmit(ProceedingJoinPoint joinPoint) throws Throwable {
        final Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        final ReSubmit annotation = method.getAnnotation(ReSubmit.class);
        final int delaySeconds = annotation.delaySeconds();
        //通过RequestContextHolder获取ServletRequestAttributes对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获取请求
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String sb = request.getRequestURL() +
                request.getRemoteAddr() + joinPoint.getSignature().getDeclaringTypeName() +
                "." +
                joinPoint.getSignature().getName() +
                Arrays.toString(joinPoint.getArgs());
        //设置key值
        String key = ResubmitLock.handleKey(sb);
        //执行加锁
        boolean lock = false;
        try {
            lock = ResubmitLock.getInstance().lock(key, PRESENT);
            if (lock) {
                //放行
                return joinPoint.proceed();
            } else {
                //响应重复提交异常
                return ResponseJsonData.errorMsg("请勿重复提交");
            }
        } finally {
            //解锁
            ResubmitLock.getInstance().unLock(lock, key, delaySeconds);
        }
    }
}
