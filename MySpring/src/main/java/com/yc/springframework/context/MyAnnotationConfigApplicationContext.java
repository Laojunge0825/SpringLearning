package com.yc.springframework.context;

import com.yc.springframework.stereotype.*;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

/**
 * @program: Spring01
 * @author: 作者
 * @create: 2021-04-05 14:14
 */
public class MyAnnotationConfigApplicationContext implements  MyApplicationContext{

  private Map<String ,Object >beanMap =new HashMap<String ,Object>();
    public MyAnnotationConfigApplicationContext(Class<?>... componentClasses) {
        try {
            register(componentClasses);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private  void register(Class<?>[] componentClasses) throws IllegalAccessException, InstantiationException, InvocationTargetException, IOException, ClassNotFoundException {
        //请实现这个里面的代码
        //源码1  只实现IOC  MyPostConstruct  MyPreDestroy
        if(componentClasses ==null||componentClasses.length<=0){
            throw new RuntimeException("没有指定配置类");
        }
        //迭代数组  找所有的配置容器
        for(Class c:componentClasses){
            if(!c.isAnnotationPresent(MyConfiguration.class)){
                continue;
            }
            String [] basePackages=getAppConfigBasePackages(c);
           if(c.isAnnotationPresent(MyComponentScan.class)){
               MyComponentScan mc= (MyComponentScan) c.getDeclaredAnnotation(MyComponentScan.class);
               if(mc.basePAckages()!=null&&mc.basePAckages().length>0){
                   basePackages=mc.basePAckages();
               }
           }
            //处理@MyBean的情况
            Object obj =c.newInstance();//obj就是当前解析的MyAppConfig对象
           handleAtMyBean(c,obj);
           for(String basePackage: basePackages){
               System.out.println("扫描包路径："+basePackage);
               scanPackageAndSubPAckage(basePackage);
           }
            //处理@MyBean 的情况
            Object o =c.newInstance(); //o 就是当前解析的  MyAppConfig 对象
           handleAtMyBean(c,o);
           //处理  basePackages 基础包下的所有的托管bean
            for (String basePackage :basePackages){
                scanPackageAndSubPAckage(basePackage);
            }
            //继续托管其他bean
            handleManagedBean();

            //版本2  实现di
            handleDi(beanMap);
        }

        

       

    }

    private void handleDi(Map<String, Object> beanMap) throws InvocationTargetException, IllegalAccessException {
        Collection<Object> objectCollection =beanMap.values();
        for(Object obj:objectCollection){
            Class cls=obj.getClass();
            Method [] ms=cls.getDeclaredMethods();
            for(Method m:ms){
                if(m.isAnnotationPresent(MyAutowired.class)&&m.getName().startsWith("set")){
                            invokeAutowiredMethod(m,obj);
                }else if(m.isAnnotationPresent(MyResource.class)&&m.getName().startsWith("set")){
                    invokeResourceMethod(m,obj);
                }
            }
            Field [] fs=cls.getDeclaredFields();
            for(Field f:fs){
                if(f.isAnnotationPresent(MyAutowired.class)){

                }else if (f.isAnnotationPresent(MyResource.class)){

                }
            }
        }
    }

    private void invokeResourceMethod(Method m, Object obj) throws InvocationTargetException, IllegalAccessException {
        //1. 取出  MyResource 中的 name 属性值  当成beanId
        MyResource mr=m.getAnnotation(MyResource.class);
        String beanId=mr.name();
        //2.如果没有  则取出  m方法中参数的类型名  改成首字母小写  当成beanID
        if(beanId==null||beanId.equalsIgnoreCase("")){
            String pname=m.getParameterTypes()[0].getSimpleName();
            beanId =pname.substring(0,1).toLowerCase()+pname.substring(1);
        }
        //3.从beanMap中取出
        Object o=beanMap.get(beanId);
        //4. invoke
        m.invoke(obj,o);
    }

    private void invokeAutowiredMethod(Method m, Object obj) throws InvocationTargetException, IllegalAccessException {
        //1. 取出  m 的参数的类型
        Class typeClass =m.getParameterTypes()[0];
        //2. 从beanMap中循环所有的object
        Set<String >keys=beanMap.keySet();
        for (String key :keys){
            Object o=beanMap.get(key);
            //3.判断 这些object是否是为  参数类型的实例    instanceof
            Class [] interfaces =o.getClass().getInterfaces();
           for(Class c:interfaces){
               if(c==typeClass){
                   //if(o.getClass().getName().equalsIgnoreCase(typeClass.getName())){
                   //4.如果是  则从beanMap中取出

                   m.invoke(obj,o);
               }
           }
        }
    }

    /**
     * 处理managedBeanClasses 所有的class类  筛选出所有的@Component @Service @Repository的类 并实例化  存到beanMap中
     */
    private void handleManagedBean() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        for (Class c:managedBeanClass){
            if(c.isAnnotationPresent(MyComponent.class)){
                saveManagedBean(c);
            }else if(c.isAnnotationPresent(MyService.class)){
                saveManagedBean(c);
            }else if(c.isAnnotationPresent(MyRepository.class)){
                saveManagedBean(c);
            }else if(c.isAnnotationPresent(MyController.class)){
                saveManagedBean(c);
            }else{
                continue;
            }
        }
    }

    private void saveManagedBean(Class c) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        Object o =c.newInstance();
        handlePostConstruct(o,c);
        String beanId=c.getSimpleName().substring(0,1).toLowerCase()+c.getSimpleName().substring(1);
        beanMap.put(beanId,o);
    }

    /**
     * 扫描包和子包
     * @param basePackage
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void scanPackageAndSubPAckage(String basePackage) throws IOException, ClassNotFoundException {
        String packagePath =basePackage.replaceAll("\\.","/");
        System.out.println("扫描包路径："+basePackage+",替换后:"+packagePath);
        Enumeration<URL> files =Thread.currentThread().getContextClassLoader().getResources(packagePath);//得到当前classPath  绝对路径的URL表示法
        //l类加载器 加载包路径 basePackage
        while(files.hasMoreElements()){//存在下一级  即当前路径的文件夹不为空
            URL url=files.nextElement();
            System.out.println("配置的扫描路径为："+url.getFile());
            findClassesInPAckages(url.getFile(),basePackage); //第二个参数  com.yc.bean
        }
    }
    private Set<Class> managedBeanClass =new HashSet<Class>();

    /**
     * 查找  file下面的子包及所有要托管的class 存到一个Set(managedBeanClasses)中
     * @param file
     * @param basePackage
     */
    private void findClassesInPAckages(String file, String basePackage) throws ClassNotFoundException {
        File f=new File(file);
        File [] classFiles=f.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.getName().endsWith(".class")||file.isDirectory();
            }
        });
        for (File cf:classFiles){
            if(cf.isDirectory()){
                //是目录 则递归
                basePackage += "." + cf.getName().substring(cf.getName().lastIndexOf("/") + 1);
                findClassesInPAckages(cf.getAbsolutePath(),basePackage);
            }else{
                //加载 cf 最为 class文件   反射加载 一次性可以加载多个   Class.forName() 一次加载一个
                URL[] urls=new URL[]{};
                URLClassLoader ucl =new URLClassLoader(urls);
                //com.yc.bean.Hello.class -> com.yc.bean.Hello
                Class c=ucl.loadClass(basePackage+"."+cf.getName().replace(".class",""));
                managedBeanClass.add(c);
            }
        }
    }

    /**
     * 处理  MyAppConfig 配置类中的@Bean 注解，完成IOC操作
     * @param cls
     * @param obj
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void handleAtMyBean(Class cls, Object obj) throws InvocationTargetException, IllegalAccessException {
        //1. 获取cls 中的所有method
        Method [] ms=cls.getDeclaredMethods();
        //2.循环  判断每个方法上是否有@MyBean
        for(Method m:ms){
            if(m.isAnnotationPresent(MyBean.class)){
                // 3.有  则激活 invoke 有返回值 存到备案Map 键是方法名  值是返回值 对象
                Object o=m.invoke(obj);
                // TODO 加入处理  @MYBean注解对应的方法所实例化的类中的 @MyPostConstruc 对应的方法
                handlePostConstruct(o,o.getClass()); //o 是值Helloworld对象   o.getClass() 它的反射对象
                beanMap.put(m.getName(),o);
            }
        }
    }

    private void handlePostConstruct(Object obj, Class<?> cls) throws InvocationTargetException, IllegalAccessException {
        Method [] ms=cls.getDeclaredMethods();

        for(Method m:ms){
            if(m.isAnnotationPresent(MyPostConstruct.class)){
               m.invoke(obj);
            }
        }
    }

    private String[] getAppConfigBasePackages(Class cls) {
        String [] paths=new String [1];
        paths[0] =cls.getPackage().getName();
        return paths;
    }

    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }
}
