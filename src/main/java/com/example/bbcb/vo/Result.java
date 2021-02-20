package com.example.bbcb.vo;

import com.example.bbcb.exception.BusinessExceptionStatusEnum;
import com.example.bbcb.exception.CustomException;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {
    //状态码
    private Integer code;
    //提示信息
    private String msg;
    //结果体
    private Object data; //private T data;

    public Result(){

    }
    public Result(BusinessExceptionStatusEnum statusEnum){
        this.setCode(statusEnum.getCode()).setMsg(statusEnum.getMsg());
    }
    public Result(BusinessExceptionStatusEnum statusEnum,String errMsg){
        this.setCode(statusEnum.getCode()).setMsg(statusEnum.getMsg()+":"+errMsg);
    }
    public Result(CustomException cex){
        this.setCode(cex.getCode()).setMsg(cex.getMessage());
    }
    public Result reset(BusinessExceptionStatusEnum statusEnum){
        this.setCode(statusEnum.getCode()).setMsg(statusEnum.getMsg());
        return this;
    }
    public Integer getCode() {
        return code;
    }
    public Result setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }
    public Result setMsg(String message) {
        this.msg = message;
        return this;
    }

    public Object getData() {
        return data;
    }
    public Result setData(Object data) {
        this.data = data;
        return this;
    }
    public static Result successResult(){
        return new Result(BusinessExceptionStatusEnum.Success);
    }


}
