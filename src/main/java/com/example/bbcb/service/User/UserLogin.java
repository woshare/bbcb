package com.example.bbcb.service.User;

import com.example.bbcb.dao.UserDao;
import com.example.bbcb.dto.User;
import com.example.bbcb.exception.BusinessExceptionStatusEnum;
import com.example.bbcb.exception.CustomException;
import com.example.bbcb.vo.Result;
import com.example.bbcb.service.CommBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

/**
 * Service层主要应对controller和dao，基本想法是把一些操作写的尽量小而简单，再组装成大的复杂的业务
 * @SessionScope 会话作用域
 * @RequestScope 请求作用域
 * 用哪一个作用域，也是看该服务接口的使用场景，频繁创建bean也是需要开销的，默认bean的作用域是singleton单例，即程序启动的时候，创建一次
 */

@Slf4j
@Service
@SessionScope
//@RequestScope
public class UserLogin extends CommBase{

    /*用户登录-登出
    *1，微信登录
    *2，验证码登录
    *
     */
    @Autowired
    private UserDao userDao;

    /*@Override
    @PostConstruct
    public void init(){
        beginTime = System.currentTimeMillis();
    }
    @Override
    @PreDestroy
    public void destroy(){
        endTime = System.currentTimeMillis();
        log.info("beginTime:{},endTime:{},spendTime:{}",beginTime,endTime,endTime-beginTime);
    }*/
    public Result weChatLogin(String uid) throws CustomException {
        Result result=new Result(BusinessExceptionStatusEnum.Success);
        List<User> users=new ArrayList<>();
        User user=userDao.findUsers(uid);
        if(user==null){
            throw new CustomException(BusinessExceptionStatusEnum.ServerError, BusinessExceptionStatusEnum.UserNotExistError,"user object is null");
        }
        users.add(user);
        users.add(user);
        //log.info("weChatLogin objectId:{}, uid:{},uid:{},userName:{},passWord:{},phone:{},registerTime:{}",objectId,uid,user.getUid(),user.getUserName(),user.getPassWord(),user.getPhoneNumber(),user.getRegisterTime());
        result.setData(users);

        return result;
    }
}
