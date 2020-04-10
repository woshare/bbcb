package com.example.bbcb.aop;

import com.example.bbcb.exception.BusinessExceptionStatusEnum;
import com.example.bbcb.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import com.alibaba.fastjson.JSONObject;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@Order(1)
public class InputArgsAuthAspect {
    /**
     * 1,校验参数，特别是json类型的参数是否格式正确
     * 2，文本过滤，色反恐等内容
     */
     //@Before("execution(public * com.example.bbcb.controller..*.*(..)) && args(req)")
     public void jsonFormatValidation(String req) throws CustomException {
        try {
            JSONObject jsonObject = JSONObject.parseObject(req);
        }catch (Exception e){
            throw new CustomException(BusinessExceptionStatusEnum.ClientError, BusinessExceptionStatusEnum.JsonFormatErr,e.getMessage());
        }
     }

}
