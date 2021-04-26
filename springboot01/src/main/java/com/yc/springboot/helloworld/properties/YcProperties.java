package com.yc.springboot.helloworld.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @program: Spring01
 * @author: 作者
 * @create: 2021-04-24 10:28
 */
@ConfigurationProperties(prefix = "yc")
//@Configuration
public class YcProperties {

    private String tname;
    private int age;

    public void setTname(String tname) {
        this.tname = tname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTname() {
        return tname;
    }

    public int getAge() {
        return age;
    }
}
