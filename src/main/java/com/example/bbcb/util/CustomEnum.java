package com.example.bbcb.util;

import lombok.Getter;

public enum CustomEnum {
    /**自定义枚举类型
     *
     */
     Roler(1,1000,"角色");

    @Getter
    private int type;//类型
    @Getter
    private int code;//状态码
    @Getter
    private String msg;//状态信息

    CustomEnum(int type,int code,String msg){
        this.type=type;
        this.code=code;
        this.msg=msg;
    }
}
