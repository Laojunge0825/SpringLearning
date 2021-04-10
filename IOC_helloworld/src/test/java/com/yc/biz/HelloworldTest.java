package com.yc.biz;

import com.yc.AppConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloworldTest  {   //测试用例
    private ApplicationContext ac; //spring 容器

    @Before
    public void setUp() throws Exception {
        ac=new AnnotationConfigApplicationContext(AppConfig.class);
        // AnnotationConfigApplicationContext 基于注解的配置类容器
        //读取 AppConfig.class ->basePackages ="com.yc" -> 得到要扫描的路径
        // 检查这些包中的类是否有@Component  注解  如有 则实例化
        // 存到一个MAp<String,Object>  =====ac
    }
    @Test
    public void testhello(){
        Helloworld hw= (Helloworld) ac.getBean("helloworld");
        hw.hello();
        Helloworld hw2= (Helloworld) ac.getBean("helloworld");
        hw2.hello();
        //spring 容器  单例模式
    }
}