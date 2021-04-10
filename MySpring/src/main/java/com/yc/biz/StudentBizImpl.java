package com.yc.biz;

import com.yc.Dao.StudentDao;
import com.yc.springframework.stereotype.MyAutowired;

/**
 * @program: Spring01
 * @author: 作者
 * @create: 2021-04-04 14:49
 */

public class StudentBizImpl {
    private  StudentDao studentDao;

    public StudentBizImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public StudentBizImpl() {
    }

    @MyAutowired //org.springframework
  //  @Resource(name = "studentDaoJpaImpl")  //如果有多个对象可以注入  必须要用@Named("")  如果只有一个对象则不需要
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public int add(String name){
        System.out.println("=========业务层=========");
        System.out.println("用户名是否重名");
        int result =studentDao.add("张三");
        System.out.println("...............");
        return result;
    }
    public void update(String name){
        System.out.println("=========业务层=========");
        System.out.println("用户名是否重名");
        studentDao.update("张三");
        System.out.println("...............");

    }
}
