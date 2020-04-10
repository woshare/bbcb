package com.example.bbcb.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 *
 *
 * 日志命名规范：appName_logType_logName.log
 * 对trace/debug/info级别的日志输出，必须使用条件输出形式或者使用占位符的方式
 * logger.error(各类参数或者对象toString + "_" + e.getMessage(), e);
 */
@Aspect
@Component
@Slf4j
@Order(1)
public class LogAspect {
    public long beginTime;
    public long endTime;
    @Pointcut("execution(public * com.example.bbcb.service..*.*(..))")//切入点描述
    public void serviceLog(){}//签名，可以理解成这个切入点的一个名称

    @Before("serviceLog()")
    public void logBeforeController(JoinPoint joinPoint) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        beginTime=System.currentTimeMillis();
        // 记录下请求内容
        log.info("################URL : {}", request.getRequestURL());
        log.info("################path : {}", request.getServletPath());
        log.info("################HTTP_METHOD : {}", request.getMethod());
        log.info("################IP : {}", request.getRemoteAddr());
        log.info("################THE ARGS OF THE CONTROLLER : {}", Arrays.toString(joinPoint.getArgs()));

        //getSignature().getDeclaringTypeName()是获取包+类名的  joinPoint.getSignature.getName()获取方法名
        log.info("################CLASS_METHOD : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        //logger.info("################TARGET: " + joinPoint.getTarget());//返回的是需要加强的目标类的对象
        //logger.info("################THIS: " + joinPoint.getThis());//返回的是经过加强后的代理类的对象
    }

    @After("serviceLog()")
    public void logAfterController(JoinPoint joinPoint) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        endTime=System.currentTimeMillis();
        // 记录下请求内容

        log.info("################beginTime:{},endTime:{},spendTime:{}",beginTime,endTime,endTime-beginTime);
    }

}
