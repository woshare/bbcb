package com.example.bbcb.util;

import com.example.bbcb.annotation.Fruit;
import com.example.bbcb.controller.UserController;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

@Service
@Slf4j
public class IdProviderUtilTest {

    //@Fruit("apple")
    public String fruitName = "";
    @Value("test")
    public String testVal;

    public String changeStr(String s) {
        s += "changed";
        return s;
    }
    public long  convert(int Num,int r)
    {
        int N=Num;
        long result=0;
        int t=0;
        while(N/r>0){
            int surp=N%r;
            N=N/r;
            result=surp*(long)Math.pow(10,t)+result;
            ++t;
        }
        result=N*(long)Math.pow(10,t)+result;
        return result;
    }
    public long[] getMins(long p[],int len){
        long[] mins=new long[100];
        mins[0]=p[0];
        for(int i=1;i<len;i++){
            mins[i]=mins[i-1]>p[i]?p[i]:mins[i-1];
        }
        return mins;
    }
	enum TESTEnum{
		ZeroEnum,
		OneEnum,
		TwoEnum
	};
    @Test
    public void createUUID() {
				log.info("enum:{},{}",TESTEnum.valueOf("ZeroEnum"),TESTEnum.TwoEnum.ordinal());

        String pic="hello.jpg";
        log.info("word:{}", pic.substring(0,pic.indexOf(".")));
        //log.info("word:{}", StringUtils.substringBeforeLast(pic,'.'));
        String uuid = IdProviderUtil.createUUID();
        long  res=convert(30,14);
        log.info("res:{}", res);

        long[] p={10,20,30,5,10,11,15,21,15,16,17};
        long[] mins=getMins(p,11);
        log.info("mins:{}", mins);
        Long la = 2L;
        String ss = "xx";
        String str = changeStr(ss);
        log.info("uuid:{}", uuid);
        log.info("annotation fruit:{},testVal:{}", fruitName, testVal);
        log.info("Long:{}", la);
        log.info("ss:{},str:{}", ss, str);

        Method[] methods = UserController.class.getDeclaredMethods();
        for (Method method : methods) {
            Fruit fruit = method.getAnnotation(Fruit.class);
            if (fruit != null) {
                log.info("fruit:{}", fruit.value());
            }
            log.info("method:{}", method);
        }

        try{
            ExecutorService exec= Executors.newCachedThreadPool();
            exec.execute(new ExceptionThread());
        }catch(RuntimeException e){
            log.error("e:{}",e);
        }

    }
}