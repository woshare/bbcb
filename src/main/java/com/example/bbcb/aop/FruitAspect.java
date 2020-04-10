package com.example.bbcb.aop;

import com.example.bbcb.annotation.Fruit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class FruitAspect {

    @Pointcut("@annotation(fruit)")
    public void fruitAnnotAspect(Fruit fruit){}

    @Before("fruitAnnotAspect(fruit)")
    public void fruitBeforeController(JoinPoint joinPoint,Fruit fruit) {
        if(fruit!=null) {
            log.info("########Before########" + fruit.value());
        }
    }

    @After("fruitAnnotAspect(fruit)")
    public void fruitAfterController(JoinPoint joinPoint,Fruit fruit) {
        if(fruit!=null) {
            log.info("########After########" + fruit.value());
        }
    }
}
