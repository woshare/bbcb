package com.example.bbcb.validators;

import io.netty.util.internal.StringUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParamsValidator implements ConstraintValidator<ParamsValidatorAnnotation, String> {
    private String[] values;
    private String format;
    private boolean valuesValid;

    @Override
    public void initialize(ParamsValidatorAnnotation paramsValidatorAnnotation) {
        this.values = paramsValidatorAnnotation.value();
        this.format = paramsValidatorAnnotation.format();
        this.valuesValid=paramsValidatorAnnotation.valueValid();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = false;
        if (value == null) {
            return isValid;
        }
        //格式限定存在
        if (!StringUtil.isNullOrEmpty(this.format)) {
            isValid = isMatch(this.format, value);
            if(!isValid){//当不符合格式要求时，直接退出
                return isValid;
            }
        }
        //格式限定存在且数据符合格式要求 isValid=true，
        // 或者格式不存在 isValid=false
        for (int i = 0; i < values.length; i++) {
            //如果存在格式要求，那需要校验一下手动设定的列表数据，是否格式正确
            //valuesValid default true
            if(!StringUtil.isNullOrEmpty(this.format)&&this.valuesValid){
                if(!isMatch(this.format, values[i])){
                    return false;
                }
            }
            if (values[i].equals(value)) {
                return true;
            }
        }

        return false;
    }

    // 通过正则校验数据,这是一个基础方法
    public static boolean isMatch(String regex, String origin) {
        if (StringUtil.isNullOrEmpty(regex) || StringUtil.isNullOrEmpty(origin)) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher match = pattern.matcher(origin);
        return match.matches();
    }
}
