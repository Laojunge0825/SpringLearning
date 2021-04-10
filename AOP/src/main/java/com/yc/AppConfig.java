package com.yc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @program: Spring01
 * @author: 作者
 * @create: 2021-04-04 15:34
 */
@Configuration //表示当前的类是一个配置类
@ComponentScan(basePackages = "com.yc")//将来要托管的bean 要扫描的包及子包
@EnableAspectJAutoProxy  //启用aspectJ的支持
public class AppConfig {
//    @Bean
//    public StudentBizImpl studentBiz(){
//        return new studentBiz();
//    }

}
