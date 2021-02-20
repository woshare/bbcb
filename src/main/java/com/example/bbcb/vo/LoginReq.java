package com.example.bbcb.vo;

import com.example.bbcb.entity.WebLog;
import com.example.bbcb.util.DataCheckUtil;
import com.example.bbcb.validators.ParamsValidatorAnnotation;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)//输入参数时，驼峰转下划线
public class LoginReq {
    private String nickName;


    private  String password;

    //@NotEmpty(message = "属性分类不能为空")
    @ParamsValidatorAnnotation(value={"13221186695","13313663689"},message = "电话号码错误",format = DataCheckUtil.mobilePhoneNumberRegex,valueValid = false)
    //@ParamsValidatorAnnotation(message = "电话号码格式错误",format = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$")
    private String phoneNumber;

}
