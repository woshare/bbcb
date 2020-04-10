package com.example.bbcb.exception;

import lombok.Getter;

public class CacheException extends RuntimeException {
    //这个是用来干嘛的，作用是什么
    //private static final long serialVersionUID = -2678203134198782909L;
    @Getter
    private int code;   // 异常状态码

    public CacheException() {
        super(BusinessExceptionStatusEnum.CacheError.getMsg());
    }

    public CacheException(String message) {
        super(message);
        this.code = BusinessExceptionStatusEnum.CacheError.getCode();
    }

    public CacheException(int code, String message) {
        super(message);
        this.code = code;
    }

    public CacheException(String message, Throwable cause) {
        super(message, cause);
        this.code = BusinessExceptionStatusEnum.CacheError.getCode();
    }

    public CacheException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public CacheException(Throwable cause) {
        super(cause);
        this.code = BusinessExceptionStatusEnum.CacheError.getCode();
    }
    public CacheException(BusinessExceptionStatusEnum priBusinessExceptionStatusEnum,BusinessExceptionStatusEnum subBusinessExceptionStatusEnum) {
        super(priBusinessExceptionStatusEnum.getMsg()+"-"+subBusinessExceptionStatusEnum.getMsg());
        code = priBusinessExceptionStatusEnum.getCode()+subBusinessExceptionStatusEnum.getCode();
    }
    public CacheException(BusinessExceptionStatusEnum priBusinessExceptionStatusEnum,BusinessExceptionStatusEnum subBusinessExceptionStatusEnum,String errMsg) {
        super(priBusinessExceptionStatusEnum.getMsg()+"-"+subBusinessExceptionStatusEnum.getMsg()+":"+errMsg);
        code = priBusinessExceptionStatusEnum.getCode()+subBusinessExceptionStatusEnum.getCode();
    }
    public CacheException(BusinessExceptionStatusEnum priBusinessExceptionStatusEnum,BusinessExceptionStatusEnum subBusinessExceptionStatusEnum,String errMsg,Throwable errCause) {
        super(priBusinessExceptionStatusEnum.getMsg()+"-"+subBusinessExceptionStatusEnum.getMsg()+":"+errMsg,errCause);
        code = priBusinessExceptionStatusEnum.getCode()+subBusinessExceptionStatusEnum.getCode();
    }

}
