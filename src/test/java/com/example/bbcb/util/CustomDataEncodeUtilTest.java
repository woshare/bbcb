package com.example.bbcb.util;

import com.example.bbcb.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@Slf4j
@Service
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomDataEncodeUtilTest {

    @Test
    public void encode(){
        String filePath=System.getProperty("user.dir");
        System.out.println("filePath:"+filePath);
      try {
           String data="test bibiciba encryte";
           String encrypted = CustomDataEncodeUtil.encode(data);
           log.info("data:{},encrypted:{}",data,encrypted);
       }catch (CustomException ce){
          log.error("code:{},msg:{}",ce.getCode(),ce.getMessage());
       }
    }

    @Test
    public void decode() {
        try{
            String encrypted="FQViQhBzkWQ+FSEkZ+rsQ+MGp4l1SK4kS2eCTWDcFgU=";
            String data=CustomDataEncodeUtil.decode(encrypted);
            log.info("encrypted:{},decrypted:{}",encrypted,data);
        }catch (CustomException ce){
            log.error("code:{},msg:{}",ce.getCode(),ce.getMessage());
        }
    }

    @Autowired
    private StringEncryptor stringEncryptor;
    @Test
    public void decode2() {
        try{
            //String data="$2a$10$pm5/EGbnQ.zKUz0PCVKvJOB695zM1IBGbMD4Mfxcq8g34rFLBzf52";
            String data="ht/V1BM6d5VdoA4z6NuHlA==";
            String encrypted=stringEncryptor.decrypt(data);
            log.info("encrypted:{},decrypted:{}",encrypted,data);
        }catch (CustomException ce){
            log.error("code:{},msg:{}",ce.getCode(),ce.getMessage());
        }
    }
    @Test
    public void encode2() {
        try{
            String data="nacos";
            String encrypted=stringEncryptor.encrypt(data);
            log.info("encrypted:{},decrypted:{}",encrypted,data);
        }catch (CustomException ce){
            log.error("code:{},msg:{}",ce.getCode(),ce.getMessage());
        }
    }
}