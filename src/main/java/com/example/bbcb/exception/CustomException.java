package com.example.bbcb.exception;

import com.example.bbcb.entity.WebLog;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomException extends RuntimeException {
    @Getter
    private int code;   // 异常状态码

    private WebLog webLog;//记录日志
    public void setWebLog(WebLog webLog){
        this.webLog=webLog;
    }
    public WebLog getWebLog(){
        return this.webLog;
    }
    public CustomException(BusinessExceptionStatusEnum businessExceptionStatusEnum) {
        super(businessExceptionStatusEnum.getMsg());
        code = businessExceptionStatusEnum.getCode();
    }
    public CustomException(BusinessExceptionStatusEnum businessExceptionStatusEnum,WebLog webLog) {
        super(businessExceptionStatusEnum.getMsg());
        code = businessExceptionStatusEnum.getCode();
        this.webLog=webLog;
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
    public CustomException(int code, String message,WebLog webLog){
        super(message);
        this.code = code;
        this.webLog=webLog;
    }
}
