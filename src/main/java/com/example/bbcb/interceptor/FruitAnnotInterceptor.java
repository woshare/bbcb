package com.example.bbcb.interceptor;


import com.example.bbcb.annotation.Fruit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
@Slf4j
public class FruitAnnotInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler)throws Exception{
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method=handlerMethod.getMethod();
        Fruit fruit=method.getAnnotation(Fruit.class);
        if(fruit!=null) {
            log.info("########FruitAnnotInterceptor########" + fruit.value());
        }

        return true;
    }
}
