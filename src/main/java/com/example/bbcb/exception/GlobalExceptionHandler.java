package com.example.bbcb.exception;

import com.example.bbcb.entity.User;
import com.example.bbcb.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**处理全局抛出的异常，统一处理，则不需要到处try-catch，只要throw 异常就好
 * @RestControllerAdvice是一个组合注解，组合了@ControllerAdvice、@ResponseBody
 * 异常需要返回页面，可以使用@ControllerAdvice
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value=Exception.class)
    public Result handleException(Exception ex){
        Result result=new Result(BusinessExceptionStatusEnum.UnkownExceptionError,ex.getMessage()+"-"+ex.getCause()+"-"+ex.getStackTrace());
        log.error("code:{},msg:{}",result.getCode(),result.getMsg());
        return result;
    }
    @ExceptionHandler(value=CustomException.class)
    public Result handleCustomException(CustomException cex){
        Result result=new Result(cex);
        log.error("code:{},msg:{}",result.getCode(),result.getMsg());
        return result;
    }
}
