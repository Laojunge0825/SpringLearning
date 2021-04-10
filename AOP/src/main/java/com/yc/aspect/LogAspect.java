package com.yc.aspect;

/**
 * @program: Spring01
 * @author: 作者
 * @create: 2021-04-09 19:34
 */

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 切面类
 */
@Aspect //切面类  你要曾强的功能写到这里
@Component  //IOC注解 让其实现Spring的托管功能
public class LogAspect {
    //切入点的声明
    @Pointcut("execution(* com.yc.biz.StudentBizImpl.add*(..))")//切入点表达式  在哪些方法上增强
    private void add(){

    }
    @Pointcut("execution(* com.yc.biz.StudentBizImpl.update*(..))")
    private  void update(){

    }
    @Pointcut("execution(* com.yc.biz.StudentBizImpl.find*(..))")
    private  void find(){

    }
    @Pointcut("add()||update()")
    private void addAndUpdate(){

    }


    //增强的声明
    @Before("com.yc.aspect.LogAspect.addAndUpdate()")
    public void Log(){
        System.out.println("=========前置增强日志==========");
        Date d=new Date();
        SimpleDateFormat sd =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s=sd.format(d);
        System.out.println("执行时间："+s);
        System.out.println("=========前置增强日志结束==============");
    }
    @After("com.yc.aspect.LogAspect.addAndUpdate()")
    public void bye(JoinPoint jp){//spring是一个ioc容器  可以使用di将程序运行的信息注入到 JointPoint
            System.out.println("=====bye=====");
            //连接点中的所有信息
        Object target=jp.getTarget();
        System.out.println("目标类为："+target);
        Object method=jp.getSignature();
        System.out.println("方法："+method);
        Object [] objs=jp.getArgs();
        if(objs!=null){
            for(Object o:objs){
                System.out.println("参数："+o);
            }
        }
        System.out.println("=========bye===========");
    }

    @Around("find()")
    public Object compute(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("进到  增强了");
        long start=System.currentTimeMillis();
        Object retval =pjp.proceed();
        long end =System.currentTimeMillis();
        System.out.println("find运行时间："+(end-start));
        System.out.println("退出增强");
        return retval;
    }
}
