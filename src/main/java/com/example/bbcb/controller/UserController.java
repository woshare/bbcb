package com.example.bbcb.controller;


import com.example.bbcb.annotation.Fruit;
import com.example.bbcb.exception.BusinessExceptionStatusEnum;
import com.example.bbcb.exception.CustomException;
import com.example.bbcb.vo.Result;
import com.example.bbcb.service.User.UserInformation;
import com.example.bbcb.service.User.UserLogin;
import com.example.bbcb.vo.LoginReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import javax.validation.Valid;

@Slf4j
@Api(value = "/user", description = "用户登陆、短信验证码")
@RestController
@RequestMapping("/user")
public class UserController {


    @Resource
    private UserLogin userLogin;
    @Resource
    private UserInformation userInformation;
    //@Fruit("apple")
    public String fruitName="";

    @Fruit("test1")
    @RequestMapping(method = RequestMethod.GET)
    public Result findAllUsers(@RequestParam(value = "req") String req) throws Exception {
        /*try{
            //@PathVariable(value = "uid") Integer uid
            Integer uid=1007235;
            log.info("======================================uid:{}",uid);
            return userLogin.weChatLogin(uid);
        }catch (Exception e){

        }
        return null;*/
        log.info("req:{},fruitName:{}",req,fruitName);
        userInformation.testSpendTime();
        Result result = new Result();

        result.setCode(0).setMsg("success").setData("null");
        throw new CustomException(BusinessExceptionStatusEnum.ServerError, BusinessExceptionStatusEnum.MD5Failed);
        //throw new Exception();
        //return result;
    }

    @RequestMapping(value = "/{uid}", method = RequestMethod.GET)
    public Result findUsers(@PathVariable(value = "uid") String uid) throws CustomException {
            log.info("uid:{}", uid);
            return userLogin.weChatLogin(uid);
    }

    @ApiOperation(value = "登录接口")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@Validated @RequestBody LoginReq req) throws CustomException {
        log.info("login:{}", req.toString());
//        String str=null;
//        log.info("str:{}", str.toString());
//        throw new CustomException(BusinessExceptionStatusEnum.ServerError);
        return Result.successResult();
    }
}
