package com.yc.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @program: Spring01
 * @author: 作者
 * @create: 2021-04-10 18:51
 */
@Aspect
@Component
@Order(value = 1)
public class Log3Aspect {
    @Around("execution(* com.yc.biz.StudentBizImpl.find*(..))")
    public Object compute2(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("***********compute2进到  增强了");
        long start=System.currentTimeMillis();
        Object retval =pjp.proceed();
        long end =System.currentTimeMillis();
        System.out.println("****** compute2 find运行时间："+(end-start));
        System.out.println("*******compute2退出增强");
        return retval;
    }
}
