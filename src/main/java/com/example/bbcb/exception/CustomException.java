package com.example.bbcb.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomException extends RuntimeException {
    @Getter
    private int code;   // 异常状态码

    public CustomException(BusinessExceptionStatusEnum businessExceptionStatusEnum) {
        super(businessExceptionStatusEnum.getMsg());
        code = businessExceptionStatusEnum.getCode();
    }
    public CustomException(BusinessExceptionStatusEnum priBusinessExceptionStatusEnum,BusinessExceptionStatusEnum subBusinessExceptionStatusEnum) {
        super(priBusinessExceptionStatusEnum.getMsg()+"-"+subBusinessExceptionStatusEnum.getMsg());
        code = priBusinessExceptionStatusEnum.getCode()+subBusinessExceptionStatusEnum.getCode();
    }
    public CustomException(BusinessExceptionStatusEnum priBusinessExceptionStatusEnum,BusinessExceptionStatusEnum subBusinessExceptionStatusEnum,String errMsg) {
        super(priBusinessExceptionStatusEnum.getMsg()+"-"+subBusinessExceptionStatusEnum.getMsg()+":"+errMsg);
        code = priBusinessExceptionStatusEnum.getCode()+subBusinessExceptionStatusEnum.getCode();
    }
    public CustomException(BusinessExceptionStatusEnum priBusinessExceptionStatusEnum,BusinessExceptionStatusEnum subBusinessExceptionStatusEnum,String errMsg,Throwable errCause) {
        super(priBusinessExceptionStatusEnum.getMsg()+"-"+subBusinessExceptionStatusEnum.getMsg()+":"+errMsg,errCause);
        code = priBusinessExceptionStatusEnum.getCode()+subBusinessExceptionStatusEnum.getCode();
    }

    public CustomException(BusinessExceptionStatusEnum priBusinessExceptionStatusEnum,BusinessExceptionStatusEnum subBusinessExceptionStatusEnum,Exception ex) {
        super(priBusinessExceptionStatusEnum.getMsg()+"-"+subBusinessExceptionStatusEnum.getMsg()+":"+ex.getMessage(),ex.getCause());
        code = priBusinessExceptionStatusEnum.getCode()+subBusinessExceptionStatusEnum.getCode();
    }
    public CustomException(int code, String message){
        super(message);
        this.code = code;
    }
}
