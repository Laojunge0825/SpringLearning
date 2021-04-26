package com.yc.service;

import com.yc.AppConfig;
import com.yc.tx.bean.Accounts;
import com.yc.tx.bean.OpTypes;
import com.yc.tx.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: Spring01
 * @author: 作者
 * @create: 2021-04-17 18:51
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class AccountServiceTest {

    @Autowired
    private AccountService as;


    @Test
    public void testOpenAccount(){
        Integer AccountId=as.openAccount(new Accounts(),1);
        System.out.println(AccountId);
    }
    @Test
    public void testDeposite(){
        Accounts a =new Accounts();
        a.setAccountId(3);
        Accounts aa=as.deposite(a,100.0, OpTypes.deposite.getName(),null);
        System.out.println(aa);
    }
    @Test
    public void testWithdraw(){
        Accounts a =new Accounts();
        a.setAccountId(3);
       Accounts aa= as.withdraw(a,100.0, OpTypes.deposite.getName(),null);
        System.out.println(aa);
    }

    @Test
    public void testTarnsfer(){
        Accounts ina =new Accounts();
        ina.setAccountId(2);
        Accounts out =new Accounts();
        out.setAccountId(3);
        Accounts aa=as.transfer(ina,out,100);
        System.out.println(aa);
    }
}
