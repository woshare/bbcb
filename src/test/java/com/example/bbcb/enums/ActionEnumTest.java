package com.example.bbcb.enums;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class ActionEnumTest {

    @Test
    public void testActionEnum(){
        float f1=ActionEnum.ACTION_MASTER_YES.getZwdKnow(1,"yes");
        float f2=ActionEnum.ACTION_MASTER_NO.getZwdKnow(2,"no");

        log.info("f1={},f2={},f1-msg={}",f1,f2,ActionEnum.ACTION_MASTER_YES.getMessage());
    }
}