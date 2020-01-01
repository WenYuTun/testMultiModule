package com.supermap.testMultiModule.ov;




import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: WenYutun
 * @date: 16:41 2018/12/16
 * @description: 参数校验返回类
 */
@Data
public class ValidationResult {

    //校验是否成功
    private boolean hasErrors = false;

    //存放错误信息的map
    private Map<String, String> errorMap = new HashMap<>();

    /**
     *     实现通用的通过格式化字符串信息获取错误结果的msg方法
     */
    public String getErrorMsg() {
        return StringUtils.join(errorMap.values().toArray(),",");
    }
}
