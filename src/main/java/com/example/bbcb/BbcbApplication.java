package com.example.bbcb;

import com.alibaba.fastjson.parser.ParserConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @SpringBootApplication： SpringBoot的核心注解，主要目的是开启自动配置。
 * 它是一个组合注解，主要组合了@Configurer,@EnableAutoConfiguration（核心）和@ComponentScan
 * 可以通过@SpringBootApplication(exclude={想要关闭的自动配置的类名.class})来关闭特定的自动配置
 *
 * 组件扫描是默认不开启的，这个注解开启了组件扫描（@ComponentScan，属性basePackages组件扫描基础包，例如com.example.bbcb，可以设置多个基础包），所以@Component才能使用
 *
 * @EnableTransactionManagement: 开启事务
 *
 * @EnableScheduling： 开启定时任务
 */
@EnableTransactionManagement
@EnableScheduling
@SpringBootApplication
@ServletComponentScan
public class BbcbApplication {

    public static void main(String[] args) {
        SpringApplication.run(BbcbApplication.class, args);
    }

}
