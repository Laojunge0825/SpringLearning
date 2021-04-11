package com.yc;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @program: Spring01
 * @author: 作者
 * @create: 2021-04-11 14:59
 */
public class LogAspect implements MethodInterceptor {
    private Object target;
    public LogAspect(Object target ){

        this.target=target;
    }

    public Object creatProxy(){
        Enhancer enhancer=new Enhancer();//增强器
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback( this);
        return enhancer.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("代理类对象："+o.getClass());
        System.out.println("目标类的方法："+method);
        System.out.println("方法中的参数："+objects);
        System.out.println("可以代理的方法："+methodProxy);
        if(method.getName().startsWith("add")){
            log();
        }
       Object returnvalue= method.invoke(this.target,objects);
        return returnvalue;
    }

    private void log() {
        System.out.println("===========before============");
        System.out.println(new Date());
        System.out.println("======");
    }
}
