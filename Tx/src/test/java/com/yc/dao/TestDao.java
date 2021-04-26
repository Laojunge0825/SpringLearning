package com.yc.dao;

import com.yc.AppConfig;
import com.yc.tx.bean.Accounts;
import com.yc.tx.bean.OpRecord;
import com.yc.tx.bean.OpTypes;
import com.yc.tx.dao.AccountDaoImpl;
import com.yc.tx.dao.OpRecordDaoImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @program: Spring01
 * @author: 作者
 * @create: 2021-04-14 21:22
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class TestDao {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private AccountDaoImpl accountDao;
    @Autowired
    private OpRecordDaoImpl opRecordDao;
    @Test
    public void testDataSource() throws SQLException {
        Assert.assertNotNull(dataSource);
        System.out.println(dataSource.getConnection());
    }
    @Test
    public void testAccountDaoImpl() throws SQLException {
        Assert.assertNotNull(accountDao);

    }

    @Test
    public void testSaveAccounts() throws SQLException {
        Accounts a=new Accounts();
        a.setBalance(10.0);
        int accountId=accountDao.saveAccount(a);
        System.out.println("新开用户Id:"+accountId);

    }
    @Test
    public void testFindAll() throws SQLException {
        List<Accounts>list=accountDao.findAll();
        System.out.println(list);

    }
    @Test
    public void testFindByID() throws SQLException {
        Accounts a=this.accountDao.findAccount(2);
        System.out.println(a);

    }

    @Test
    public void testSaveOpRecord() throws SQLException {
        OpRecord op=new OpRecord();
        op.setAccounId(3);
        op.setOpmoney(100.00);
        op.setOptime(new Timestamp(new Date().getTime()));
        op.setOptype(OpTypes.deposite.getName()); //用枚举做这个值 不容易出错
        op.setTransferid(" ");
        opRecordDao.saveOpRecord(op);
        System.out.println(op);

    }
    @Test
    public void testOpRecordFindAll() throws SQLException {
       List<OpRecord>list=opRecordDao.findAll();
       System.out.println(list);

    }
    @Test
    public void testOpRecordFindByid() throws SQLException {
        List<OpRecord>list=opRecordDao.findByAccountId(2);
        System.out.println(list);

    }
}
