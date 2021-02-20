package com.example.bbcb.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Constraint(validatedBy = ParamsValidator.class)
public @interface ParamsValidatorAnnotation {
    String[] value() default {};//必要项

    String message() default "param is error";//必要项

    Class<?>[] groups() default {};//必要项

    Class<? extends Payload>[] payload() default {};//必要项

    String format() default "";//格式判断

    boolean valueValid() default true;//是否需要校验手动输入的数据列表
}
