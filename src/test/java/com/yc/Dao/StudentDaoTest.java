//package com.yc.Dao;
//
//import com.yc.biz.StudentBizImpl;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//public class StudentDaoTest {
//
//    private ApplicationContext ac;
//    private StudentDao studentDao;
//    private StudentBizImpl studentBiz;
//    @org.junit.Before
//    public void setUp() throws Exception {
//          ac=new AnnotationConfigApplicationContext();
////        //1. 能否自动完成  实例化对象  -》 通过  IOC 控制  反转 -》由容器实例化对象
////        studentDao=new StudentDaoMybatisImpl();
////         //  studentBiz=new StudentBizImpl(studentDao); // IOC
////        studentBiz =new StudentBizImpl();
////        //2. 能否自动完成装配过程  -》  通过  DI 依赖注入  -》由容器装配对象
////        studentBiz.setStudentDao(studentDao);
//
//    }
//
//    @org.junit.Test
//    public void add() {
//            studentDao.add("张三");
//    }
//
//    @org.junit.Test
//    public void update() {
//        studentDao.add("张三");
//    }
//}