package com.example.bbcb.exception;

import com.example.bbcb.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 处理全局抛出的异常，统一处理，则不需要到处try-catch，只要throw 异常就好
 *
 * @RestControllerAdvice是一个组合注解，组合了@ControllerAdvice、@ResponseBody 异常需要返回页面，可以使用@ControllerAdvice
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获数据校验的错误
     * 注意import MethodArgumentNotValidException的包
     * @param mex
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handleArgumentNotValidException(MethodArgumentNotValidException mex) {
        StringBuilder errMsg = new StringBuilder();
        for (ObjectError error : mex.getBindingResult().getAllErrors()) {
            //获取校验的信息
            errMsg.append(error.getDefaultMessage()).append(",");
        }
        Result result = new Result(BusinessExceptionStatusEnum.ParametersWrongError, errMsg.toString());
        log.error("code:{},msg:{}", result.getCode(), result.getMsg());
        return result;
    }

    /**
     * 捕获其他未注明的异常
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Result handleException(Exception ex) {
        Result result = new Result(BusinessExceptionStatusEnum.UnkownExceptionError, ex.getMessage() + "-" + ex.getCause() + "-" + ex.getStackTrace());
        log.error("code:{},msg:{}", result.getCode(), result.getMsg());
        return result;
    }

    /**
     * 捕获自定义异常
     * @param cex
     * @return
     */
    @ExceptionHandler(value = CustomException.class)
    public Result handleCustomException(CustomException cex) {
        Result result = new Result(cex);
        log.error("code:{},msg:{}", result.getCode(), result.getMsg());
        return result;
    }
}
