package com.example.bbcb.config;

import com.example.bbcb.interceptor.FruitAnnotInterceptor;
import com.example.bbcb.interceptor.ProcessPerformanceInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        //registry.addInterceptor(new MyInterceptor1()).addPathPatterns("/**");
        registry.addInterceptor(new ProcessPerformanceInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new FruitAnnotInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

}
