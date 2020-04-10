package com.example.bbcb.service.User;

import com.example.bbcb.service.CommBase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Service
public class UserInformation extends CommBase {


    public void testSpendTime(){
        log.info("beginTime:{},endTime:{}",beginTime,endTime);
    }

}
