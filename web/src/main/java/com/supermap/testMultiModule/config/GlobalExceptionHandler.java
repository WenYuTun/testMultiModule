package com.supermap.testMultiModule.config;

import com.supermap.testMultiModule.ov.ResponseJsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: WenYutun
 * @date: 14:48 2018/12/9
 * @description:统一异常处理类
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 日志
     */
    private final static Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    private ResponseJsonData exceptionHandler(Exception e){
        LOG.error("Exception>>>>>>>>",e);
        e.printStackTrace();
        //返回自定义异常信息
        return ResponseJsonData.errorException(e.getMessage());
    }

}
