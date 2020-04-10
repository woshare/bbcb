package com.example.bbcb.config;

import com.example.bbcb.filter.UGCContentsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean UGCContentFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new UGCContentsFilter());
        registration.addUrlPatterns("/*");
        registration.setName("UGCContentsFilter");
        registration.setOrder(1);
        return registration;
    }
}
