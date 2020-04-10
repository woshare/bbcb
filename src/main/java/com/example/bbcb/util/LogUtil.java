package com.example.bbcb.util;

import java.text.MessageFormat;

public class LogUtil {
    /*日志规范
    *
    *
     */
    public static String LogStringFormat(String topic,String uri,Long objectId,String uid,String className,String funcName,String lineNum,String cause,String msg){
        String logStr= MessageFormat.format("topic:{0},uri:{1},objectId:{2},uid:{3},className:{4},funcName:{5},lineNum:{6},cause:{7},msg:{8}",topic,uri,objectId,uid,className,funcName,lineNum,cause,msg);
        return logStr;
    }
}
