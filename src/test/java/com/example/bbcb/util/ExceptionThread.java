package com.example.bbcb.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceptionThread implements Runnable {
    public void run(){
        log.info("run");
       // throw new RuntimeException();
    }
}
