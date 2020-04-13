package com.example.bbcb.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum ActionEnum {
   ACTION_MASTER_YES(22, "标记熟词", "已标记为熟词", 0, 0, 0, 0f, 0f, 0f) {
        @Override
        public float getZwdKnow(Integer code, String message) {
            return getZwd("str1", code);
        }
    },
    ACTION_MASTER_NO(22, "标记熟词", "已标记为熟词", 0, 0, 0, 0f, 0f, 0f) {
        @Override
        public float getZwdKnow(Integer code, String message) {
            return getZwd("str1", code);
        }
    };

    private Integer code;
    private String message;
    private String content;
    private Integer dailyLimitKnow;//daily limits of reading      0：不需要限制
    private Integer dailyLimitListen;//daily limits of listening  0：不需要限制
    private Integer dailyLimitSpeak;//daily limits of speak       0：不需要限制
    //有一部分只直接得到掌握度的设定值，有的是需要通过计算得到
    private Float zwdKnow;//认的掌握度初始值
    private Float zwdListen;//听的掌握度初始值
    private Float zwdSpeak;//说的掌握度初始值

    public abstract float getZwdKnow(Integer code, String message);

//    public abstract float getZwdListen(Integer code, String message);
//
//    public abstract float getZwdSpeak(Integer code, String message);

    ActionEnum(Integer code, String message, String content, Integer dailyLimitKnow, Integer dailyLimitListen, Integer dailyLimitSpeak, Float zwdKnow, Float zwdListen, Float zwdSpeak) {
        this.code = code;
        this.message = message;
        this.content = content;
        this.dailyLimitKnow = dailyLimitKnow;
        this.dailyLimitListen = dailyLimitListen;
        this.dailyLimitSpeak = dailyLimitSpeak;
        this.zwdKnow = zwdKnow;
        this.zwdListen = zwdListen;
        this.zwdSpeak = zwdSpeak;
    }

    //根据action的code获取对应的枚举值
    public static ActionEnum getZsLogsActionEnumByCode(int code) {
        for (ActionEnum actionEnum : ActionEnum.values()) {
            if (actionEnum.getCode().intValue() == code) {
                return actionEnum;
            }
        }
        return null;
    }

    public static float getZwd(String str1, Integer in2) {
        if (in2.intValue() == 1) {
            return 1.0f;
        } else {
            return 2.0f;
        }
    }
}
