package com.example.bbcb.exception;

import com.alibaba.fastjson.JSON;
import com.example.bbcb.entity.WebLog;
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
        Result result = new Result(BusinessExceptionStatusEnum.ParametersWrongError, errMsg.toString().substring(0,errMsg.toString().length()-1));
        log.error("code:{},msg:{}", result.getCode(), result.getMsg());
        int stackTraceSize=mex.getStackTrace().length;
        String declareClass=mex.getStackTrace()[0].getClassName();
        String fileName=mex.getStackTrace()[0].getFileName();
        String methodName=mex.getStackTrace()[0].getMethodName();
        int lineNumber=mex.getStackTrace()[0].getLineNumber();
        System.out.println("stackTraceSize:"+stackTraceSize+",declareClass:"+declareClass+",fileName:"+fileName+",methodName:"+methodName+",lineNumber:"+lineNumber);
        int stackTraceNum=0;
        for(StackTraceElement stackTraceElement:mex.getStackTrace()){
            System.out.println("stackTraceNum:"+stackTraceNum+++",declareClass:"+stackTraceElement.getClassName()+",fileName:"+stackTraceElement.getFileName()+",methodName:"+stackTraceElement.getMethodName()+",lineNumber:"+stackTraceElement.getLineNumber());
        }
        return result;
    }

    /**
     * 捕获其他未注明的异常
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Result handleException(Exception ex) {
        Result result = new Result(BusinessExceptionStatusEnum.UnkownExceptionError, ex.getMessage() + "-" + ex.getCause());
        log.error("code:{},msg:{}", result.getCode(), result.getMsg());
        int stackTraceSize=ex.getStackTrace().length;
        String declareClass=ex.getStackTrace()[0].getClassName();
        String fileName=ex.getStackTrace()[0].getFileName();
        String methodName=ex.getStackTrace()[0].getMethodName();
        int lineNumber=ex.getStackTrace()[0].getLineNumber();
        System.out.println("stackTraceSize:"+stackTraceSize+",declareClass:"+declareClass+",fileName:"+fileName+",methodName:"+methodName+",lineNumber:"+lineNumber);
        int stackTraceNum=0;
        for(StackTraceElement stackTraceElement:ex.getStackTrace()){
            System.out.println("stackTraceNum:"+stackTraceNum+++",declareClass:"+stackTraceElement.getClassName()+",fileName:"+stackTraceElement.getFileName()+",methodName:"+stackTraceElement.getMethodName()+",lineNumber:"+stackTraceElement.getLineNumber());
        }

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
        String resultMsg=result.getMsg();
        WebLog webLog=cex.getWebLog();
        if(webLog!=null){
            resultMsg=result.getMsg()+"(wid:"+cex.getWebLog().getWid()+")";
        }else{//会出现，没有被aop around捕获的自定义异常吗？
            webLog=new WebLog();
            webLog.setStackTraceElement(cex.getStackTrace()[0]);
            webLog.setUsername("uid");
            String cause=""+cex.getCause();
            String message=cex.getMessage();
            webLog.setExceptionCause(cause);
            webLog.setExceptionMessage(message);
        }
        result.setMsg(resultMsg);
        webLog.setRspResult(result);
        long endTime = System.currentTimeMillis();
        webLog.setEndTime(endTime);
        webLog.setSpendTime(endTime-webLog.getStartTime());
        String webLogJson= JSON.toJSONString(webLog);
        log.error("web customException:{}",webLogJson);
        System.out.println("web customException:"+webLogJson);
        return result;
    }
}
