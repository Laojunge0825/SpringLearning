package com.yc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

/**
 * @program: Spring01
 * @author: 作者
 * @create: 2021-04-10 20:26
 */
public class LogAspect implements InvocationHandler {
    private Object target;//1.引入目标类的对象
    public LogAspect (Object target){
        this.target=target;
    }
    //2.生成代理对象
    public Object creatProxy(){
        return Proxy.newProxyInstance(this.target.getClass().getClassLoader(),this.target.getClass().getInterfaces(),this);
    }

    //3. 回调方法  当jvm带哦用代理对象的被代理的方法时  会由jvm自动调用这个invoke
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理类对象："+proxy.getClass());
        System.out.println("目标类的方法："+method);
        System.out.println("方法中的参数："+args);

        if(method.getName().startsWith("add")){  //转换成切入点表达式
            //前置增强
            log();
        }
        Object returnvalue= method.invoke(this.target,args);  //相当于执行了StudentBizImpl 中的 find();
        return returnvalue;
    }

    private void log() {
        System.out.println("==========before============");
        System.out.println(new Date());
        System.out.println("======");
    }
}
