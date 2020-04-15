package com.example.bbcb.aop;

import com.alibaba.fastjson.JSON;
import com.example.bbcb.entity.WebLog;
import com.example.bbcb.exception.BusinessExceptionStatusEnum;
import com.example.bbcb.exception.CustomException;
import com.example.bbcb.vo.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

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
    /**
     * 这个类，只会实例化一次，其中的属性元素是贡献的
     * 所以不太适合@Before和@After，需要用@Around
     */

    /**
     * 业务实现类的aop切入点
     */
    @Pointcut("execution(public * com.example.bbcb.controller.*.*(..))")//切入点描述
    public void serviceLog(){}//

    /**
     * 自定义参数校验注解异常处理方法的aop切入点
     */
    @Pointcut("execution(public * com.example.bbcb.exception.GlobalExceptionHandler.handleArgumentNotValidException(..))")//切入点描述
    public void ArgumentNotValidExceptionLog(){}//

    @Pointcut("execution(public * com.example.bbcb.exception.GlobalExceptionHandler.handleException(..))")//切入点描述
    public void ExceptionLog(){}//
    @Pointcut("execution(public * com.example.bbcb.exception.GlobalExceptionHandler.handleCustomException(..))")//切入点描述
    public void CustomExceptionLog(){}//

    //@Before("serviceLog()")
    //@AfterReturning(returning="result",pointcut = "serviceLog()")

    @AfterReturning(returning="result",pointcut = "ArgumentNotValidExceptionLog()")
    public void logArgumentNotValidExceptionHandleAfterReturning(JoinPoint joinPoint,Object result){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        long startTime=System.currentTimeMillis();
        WebLog webLog=new WebLog();
        webLog.setWid(UUID.randomUUID().toString());
        webLog.setStartTime(startTime);
        webLog.setUsername("uid");
        webLog.setUri(request.getRequestURI());
        webLog.setUrl(request.getRequestURL().toString());
        webLog.setHeadParameter("head parameter");
        webLog.setRemoteIP(getUserIP(request));
        webLog.setMethodType(request.getMethod());
        webLog.setMethod(joinPoint.getSignature().getDeclaringTypeName()+":"+joinPoint.getTarget());
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        webLog.setReqParameter(getParameter(method, joinPoint.getArgs()));
        long endTime=System.currentTimeMillis();
        webLog.setRspResult(result);
        webLog.setEndTime(endTime);
        webLog.setSpendTime(endTime-webLog.getStartTime());
        String webLogJson= JSON.toJSONString(webLog);
        System.out.println("LOG ArgumentNotValid:"+webLogJson);
        log.debug("LOG LOG ArgumentNotValid:{}",webLogJson);
    }
    @Around("serviceLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取当前请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        WebLog webLog = new WebLog();

        webLog.setWid(UUID.randomUUID().toString());
        long startTime = System.currentTimeMillis();
        webLog.setStartTime(startTime);
        webLog.setUsername("uid");
        webLog.setUri(request.getRequestURI());
        webLog.setUrl(request.getRequestURL().toString());
        webLog.setHeadParameter("head parameter");
        webLog.setRemoteIP(getUserIP(request));
        webLog.setMethodType(request.getMethod());
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        webLog.setMethod(joinPoint.getSignature().getDeclaringTypeName()+"::"+method.toString());
        webLog.setReqParameter(getParameter(method, joinPoint.getArgs()));

        if (method.isAnnotationPresent(ApiOperation.class)) {
            ApiOperation log = method.getAnnotation(ApiOperation.class);
            webLog.setApiOperationDesc(log.value());
        }
        Object result=null;
        try {
            result = joinPoint.proceed();//执行相应切入的方法
            webLog.setRspResult(result==null?"":result);
        }catch (CustomException cex){//捕获到自定义异常时，记录异常栈信息，并接着抛出这个自定义异常
//            String declareClass=cex.getStackTrace()[0].getClassName();
//            String fileName=cex.getStackTrace()[0].getFileName();
//            String methodName=cex.getStackTrace()[0].getMethodName();
//            int lineNumber=cex.getStackTrace()[0].getLineNumber();
//            webLog.setExceptionTraceDeclareClass(declareClass);
//            webLog.setExceptionTraceFileName(fileName);
//            webLog.setExceptionTraceMethodName(methodName);
//            webLog.setExceptionTraceLineNumber(lineNumber);
            String cause=""+cex.getCause();
            String message=""+cex.getMessage();//null转"null"
            webLog.setExceptionCause(cause);
            webLog.setExceptionMessage(message);
            webLog.setStackTraceElement(cex.getStackTrace()[0]);
            cex.setWebLog(webLog);
            throw cex;
        }catch (Exception ex){//捕获到非自定义异常，记录异常栈信息，并抛出自定义异常
//            int stackTraceNum=0;
//            for(StackTraceElement stackTraceElement:ex.getStackTrace()){
//                System.out.println("stackTraceNum log aspect:"+stackTraceNum+++",declareClass:"+stackTraceElement.getClassName()+",fileName:"+stackTraceElement.getFileName()+",methodName:"+stackTraceElement.getMethodName()+",lineNumber:"+stackTraceElement.getLineNumber());
//            }
//            String declareClass=ex.getStackTrace()[0].getClassName();
//            String fileName=ex.getStackTrace()[0].getFileName();
//            String methodName=ex.getStackTrace()[0].getMethodName();
//            int lineNumber=ex.getStackTrace()[0].getLineNumber();
//            webLog.setExceptionTraceDeclareClass(declareClass);
//            webLog.setExceptionTraceFileName(fileName);
//            webLog.setExceptionTraceMethodName(methodName);
//            webLog.setExceptionTraceLineNumber(lineNumber);
            String cause=""+ex.getCause();
            String message=""+ex.getMessage();
            webLog.setExceptionCause(cause);
            webLog.setExceptionMessage(message);
            webLog.setStackTraceElement(ex.getStackTrace()[0]);
            throw new CustomException(BusinessExceptionStatusEnum.UnkownExceptionError.getCode(), ex.getMessage() + "-" + ex.getCause() + "-" + ex.getStackTrace(),webLog);
        }
        //当切入点方法执行正常
        long endTime = System.currentTimeMillis();
        webLog.setEndTime(endTime);
        webLog.setSpendTime(endTime-startTime);
        String webLogJson= JSON.toJSONString(webLog);
        System.out.println("LOG Success:"+webLogJson);
        log.debug("LOG Success:{}",webLogJson);
        return result;
    }

    public String getProblemTarget(){
        StringBuilder sb = new StringBuilder();
        int originStackIndex=2;
        sb.append(Thread.currentThread().getStackTrace()[originStackIndex].getFileName());
        sb.append(":");
        sb.append(Thread.currentThread().getStackTrace()[originStackIndex].getClassName());
        sb.append("::");
        sb.append(Thread.currentThread().getStackTrace()[originStackIndex].getMethodName());
        sb.append(":");
        sb.append(Thread.currentThread().getStackTrace()[originStackIndex].getLineNumber());
        return sb.toString();
    }
    public static String getUserIP(HttpServletRequest request)
    {
        String ERROR_IP="127.0.0.1";
        // 优先取 X-Real-IP
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("x-forwarded-for");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
            if ("0:0:0:0:0:0:0:1".equals(ip))
            {
                ip = ERROR_IP;
            }
        }
        if ("unknown".equalsIgnoreCase(ip))
        {
            ip = ERROR_IP;
            return ip;
        }
        int index = ip.indexOf(',');
        if (index >= 0)
        {
            ip = ip.substring(0, index);
        }

        return ip;
    }

    /**
     * 根据方法和传入的参数获取请求参数
     */
    private Object getParameter(Method method, Object[] args) {
        List<Object> argList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            //将RequestBody注解修饰的参数作为请求参数
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (requestBody != null) {
                argList.add(args[i]);
            }
            //将RequestParam注解修饰的参数作为请求参数
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if (requestParam != null) {
                Map<String, Object> map = new HashMap<>();
                String key = parameters[i].getName();
                if (!StringUtils.isEmpty(requestParam.value())) {
                    key = requestParam.value();
                }
                map.put(key, args[i]);
                argList.add(map);
            }
        }
        if (argList.size() == 0) {
            return null;
        } else if (argList.size() == 1) {
            return argList.get(0);
        } else {
            return argList;
        }
    }

}
