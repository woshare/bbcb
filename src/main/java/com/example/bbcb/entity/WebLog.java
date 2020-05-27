package com.example.bbcb.entity;

import lombok.*;

/**
 * 构想：用一个统一的对象，记录一次请求的日志数据，便于定位问题
 * 0，用aop+注解的方式，对参数进行注解校验，
 * 当报错时，会在系统层面就抛出异常（不会进入到controller），
 * 在全局异常捕获的地方，统一处理，
 * 并用aop监控这个异常处理函数，打印日志
 * 1，参数校验ok，用aop，监控一个请求进来的时候，记录这个请求的相关数据
 * 2，方法执行后，会有两种报错，一种自定义，一种非自定义
 * 3，捕获这两个异常，并把weblog对象随自定义错误类型抛出，统一处理这个异常
 * 4，在全局自定义异常捕获的方法中，打印这个日志，并把这个日志的id，添加到应答数据rsp中
 */
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WebLog {
    /**
     * 标注一个日志唯一记录
     */
    private  String wid;

    /**
     * 操作用户,比如uid，后期再不断完善，比如加一个字段记录deviceid之类的
     */
    private String username;
    /**
     * 操作描述
     * 主要是对应方法的 @ApiOperation(value = "登录接口")
     */
    private String apiOperationDesc;
    /**
     * 用户IP地址
     */
    private String remoteIP;
    /**
     * URL
     */
    private String url;
    /**
     * URI
     */
    private String uri;
    /**
     * http head参数
     */
    private Object headParameter;
    /**
     * 请求类型
     */
    private String methodType;

    /**
     * 请求处理方法
     */
    private String method;
    /**
     * 请求参数
     */
    private Object reqParameter;
    /**
     * 应答数据
     */
    private Object rspResult;
    /**
     * 操作开始时间
     */
    private Long startTime;
    /**
     * 操作结束时间
     */
    private Long endTime;
    /**
     * 消耗时间
     */
    private Long spendTime;

    /**
     *出异常的方法所在类名
     */
    //private String exceptionTraceDeclareClass;
    /**
     *出异常的方法所在文件名
     */
    //private String exceptionTraceFileName;
    /**
     *出异常的方法
     */
    //private String exceptionTraceMethodName;
    /**
     *出异常的行数
     */
    //private Integer exceptionTraceLineNumber;
    /**
     *异常原因
     */
    private String exceptionCause;
    /**
     *异常信息
     *
     */
    private String exceptionMessage;

    /**
     * 异常栈信息
     * 通常ex.getStackTrace()[0]，是指业务中报错的地方异常栈
     *  取代了如上四个元素
     */
    StackTraceElement stackTraceElement;

}
