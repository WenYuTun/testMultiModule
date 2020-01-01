package com.supermap.testMultiModule.component;

import com.supermap.testMultiModule.ov.ValidationResult;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;


/**
 * @author: WenYutun
 * @date: 17:07 2018/12/16
 * @description:
 */
@Component
public class ValidatorComponent implements InitializingBean {

    private Validator validator;

    /**
     * 实现校验方法并返回校验结果
     */
    public ValidationResult validate(Object bean) {
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<Object>> validate = validator.validate(bean);
        if (validate.size() > 0) {
            result.setHasErrors(true);
            validate.forEach(e -> {
                String errMsg = e.getMessage();
                String propertyName = e.getPropertyPath().toString();
                result.getErrorMap().put(propertyName, errMsg);
            });
        }
        return result;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //将hibernate validator通过工厂初始化方式使其实例化
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
