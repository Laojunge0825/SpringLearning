package com.yc.biz;


import com.yc.Dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * @program: Spring01
 * @author: 作者
 * @create: 2021-04-04 14:49
 */
@Repository
public class StudentBizImpl implements StudentBiz{
    private  StudentDao studentDao;

    public StudentBizImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public StudentBizImpl() {
    }

    @Autowired //org.springframework   只按类型注入  多歌同类型则需要@Qualifier()来约定注入的beanid
   @Qualifier("studentDaoJpaImpl")
  //  @Resource(name = "studentDaoJpaImpl")  //如果有多个对象可以注入  必须要用@Named("")  如果只有一个对象则不需要
    //如果没有找到同名的bean  则按类型查找
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public int add(String name){
        System.out.println("=========业务层=========");
        System.out.println("用户名是否重名");
        int result =studentDao.add(name);
        System.out.println("...............");
        return result;
    }
    public void update(String name){
        System.out.println("=========业务层=========");
        System.out.println("用户名是否重名");
        studentDao.update(name);
        System.out.println("...............");

    }
    public void find(String name){
        System.out.println("=========业务层=========");
        System.out.println("业务层查找用户："+name);
        studentDao.find(name);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("......业务操作结束.........");
    }
}
