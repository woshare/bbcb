package com.example.bbcb.util;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class SpringBeanUtil {

    /**
     *  通过实体名称得它对应的service
     * @param beanName
     * @param <T>
     * @return
     */
    public static <T> T getBean(String beanName){
        // 获取当前运行环境下Spring上下文
        WebApplicationContext webApp = ContextLoader.getCurrentWebApplicationContext();
        return (T) webApp.getBean(beanName);
    }
}
