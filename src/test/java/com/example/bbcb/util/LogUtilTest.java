package com.example.bbcb.util;

import com.example.bbcb.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;



@Slf4j
public class LogUtilTest {
    @Test
    public void LogStringFormatTest(){
        //stackTrace[] 1:当前方法执行的堆栈，2：上一级的方法的堆栈,以此类推
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int level=1;
        String logStr=LogUtil.LogStringFormat("topic","/test",Long.MAX_VALUE,"1007235",stackTrace[level].getClassName(),stackTrace[level].getMethodName(),String.valueOf(stackTrace[level].getLineNumber()),"cause","msg");
        log.info(logStr);

        List<Result> resultList0=new ArrayList<>();
        Result r1=new Result();
        r1.setCode(1);
        Result r2=new Result();
        r2.setCode(2);
        Result r3=new Result();
        r3.setCode(3);
        resultList0.add(r1);
        resultList0.add(r2);
        resultList0.add(r3);
        List<Result> resultList1=new ArrayList<>();
        resultList1.add(r1);
        resultList1.add(r2);
        for(Result res:resultList1){
            resultList0.remove(res);
        }
        for(Result res:resultList0){
            log.info("resultList0 res code:{}",res.getCode());
        }
    }
}