package com.example.bbcb.util;

import com.example.bbcb.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;
@Slf4j
public class CustomDataEncodeUtilTest {

    @Test
    public void encode(){
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
}