package com.yc.projects.bank;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;


/**
 * @program: Spring01
 * @author: 作者
 * @create: 2021-04-14 20:16
 */
@Configuration //表示当前的类是一个配置类
@ComponentScan(basePackages = "com.yc.projects.bank")//将来要托管的bean 要扫描的包及子包
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public DataSource dataSource() throws PropertyVetoException {

        DataSource ds=new ComboPooledDataSource();
        ((ComboPooledDataSource)ds).setDriverClass("com.mysql.cj.jdbc.Driver");
        ((ComboPooledDataSource)ds).setJdbcUrl("jdbc:mysql://localhost:3306/TestBank?serverTimezone=GMT%2B8");//
        ((ComboPooledDataSource)ds).setUser("root");
        ((ComboPooledDataSource)ds).setPassword("a");

        return  ds;
    }

    @Bean  //@Bean注解的优先级高于@Component @Service....  事务管理器
    public TransactionManager DataSourceTranscationManager(DataSource ds){
        return new DataSourceTransactionManager(ds);
    }
}
