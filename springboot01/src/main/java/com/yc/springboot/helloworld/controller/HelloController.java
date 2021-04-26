package com.yc.springboot.helloworld.controller;


import com.yc.springboot.helloworld.helloworld.Springboot01Application;
import com.yc.springboot.helloworld.properties.YcProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: Spring01
 * @author: 作者
 * @create: 2021-04-24 10:03
 */


public class HelloController {

    //创建日志对象   必须写当前类的反射类
    private static Log log= LogFactory.getLog(Springboot01Application.class);
    // 获取参数的方法  1.注入Environment 取值不方便  2.Di  @Value   3.采用属性文件
    @Autowired
    private Environment env;
    @Value("${yc.tname}")
    private String tname;
    @Autowired
    private YcProperties yp;

    @GetMapping("/hello")  // get请求  /hello
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        log.debug("*****debug*****");
        log.info("*****info*****");
        log.error("******error*****");
        log.fatal("******fatal*****");
        log.info("系统环境变量："+env);
        log.info("用户路径："+env.getProperty("user.home"));
        log.info("JDK环境变量"+env.getProperty("JAVA_HOME"));
        log.info(tname);
        log.info("ycProperties中的属性："+yp.getTname()+yp.getAge());
        log.info("env也能获取到YcProperties中的属性："+env.getProperty("yc.tname")+env.getProperty("yc.age"));
        return String.format("Hello %s!", name);
    }
}
