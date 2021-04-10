//package com.yc.biz;
//
//import com.yc.AppConfig;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//import static org.junit.Assert.*;
//
//public class StudentBizImplTest {
//    private ApplicationContext ac;
//    private StudentBizImpl studentBiz;
//    @Before
//    public void setUp() throws Exception {
//        ac=new AnnotationConfigApplicationContext(AppConfig.class);
//        studentBiz= (StudentBizImpl) ac.getBean("studentBizImpl");
//    }
//
//    @Test
//    public void add() {
//        studentBiz.add("张三");
//    }
//
//    @Test
//    public void update() {
//        studentBiz.add("张三");
//    }
//}