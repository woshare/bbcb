package com.example.bbcb.aop;

import com.example.bbcb.dao.UserDao;
import com.example.bbcb.exception.CustomException;
import com.example.bbcb.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Aspect
@Component
@Slf4j
@Order(1)
public class LoginAspect {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private UserDao userDao;


    @Pointcut("execution(public * com.example.bbcb.service.User.UserLogin.*(..))")//切入点描述
    public void serviceLogin() {
    }

   /* @After("execution(public * com.example.bbcb.service.User.UserLogin.*(..)) && args(uid)")
    public void loginAfterAspect(String uid) throws CustomException {

        User user = userDao.findUsers(uid);
        if (user == null) {
            throw new CustomException(BusinessExceptionStatusEnum.ServerError, BusinessExceptionStatusEnum.UserNotExistError);
        }
        log.info("loginAfterAspect date:{}, uid:{},uid:{},userName:{},passWord:{},phone:{},registerTime:{}", dateFormat.format(new Date()), uid, user.getUid(), user.getUserName(), user.getPassWord(), user.getPhoneNumber(), user.getRegisterTime());

    }*/

    @AfterReturning(returning="result",pointcut = "serviceLogin()")
    public void loginAfterReturningAspect(Object result) throws CustomException {

        Result result1=(Result)result;
           // throw new CustomException(BusinessExceptionStatusEnum.ServerError, BusinessExceptionStatusEnum.UserNotExistError);

        log.info("loginAfterReturningAspect result:{},{},{}",result1.getCode(),result1.getMsg(),result1.getData());
    }
    @AfterThrowing(pointcut = "serviceLogin()", throwing = "ex")
    public void loginAfterThrowingAspect(CustomException ex){


        // throw new CustomException(BusinessExceptionStatusEnum.ServerError, BusinessExceptionStatusEnum.UserNotExistError);

        log.info("loginAfterThrowingAspect ex:{},{}",ex.getCode(),ex.getMessage());
    }
}
