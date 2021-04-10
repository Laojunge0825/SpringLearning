package com.yc;

import com.yc.bean2.HelloWorld;
import com.yc.springframework.stereotype.MyBean;
import com.yc.springframework.stereotype.MyComponentScan;
import com.yc.springframework.stereotype.MyConfiguration;

/**
 * @program: Spring01
 * @author: 作者
 * @create: 2021-04-05 14:32
 */
@MyConfiguration
@MyComponentScan(basePAckages = {"com.yc.bean2"})
public class MyAppConfig {
    @MyBean
    public HelloWorld hw(){
        return new HelloWorld();//实例化 Helloworld
    }
}
