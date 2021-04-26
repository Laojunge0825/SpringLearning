package com.yc.projects.bank.controller;

import com.yc.projects.bank.bean.Accounts;
import com.yc.projects.bank.biz.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: Spring01
 * @author: 作者
 * @create: 2021-04-18 16:58
 */

@Controller

public class AccountController {
    @Autowired
    private AccountService accountService;
//    @RequestMapping("/save")
//    public Integer save(Accounts account, double money) {
//       Integer a=accountService.openAccount(account,money);
//     return null;
//    }
    @RequestMapping("/open")
    public @ResponseBody Integer save(@RequestParam(value = "money", defaultValue = "1") double money) {
               Accounts a=new Accounts();

        return accountService.openAccount(a,money);
     }
    @RequestMapping(value = "/deposite" ,method = RequestMethod.GET)
    public @ResponseBody Accounts deposite(Integer accountid,double money, String optype, String transferid){

        Accounts account =new Accounts();
        account.setAccountId(accountid);
        Accounts a =accountService.deposite(account,money,optype,transferid);
        return a;
    }
    @RequestMapping(value = "/withdraw" ,method = RequestMethod.GET)
    public @ResponseBody Accounts withdraw(Integer accountid,double money, String optype, String transferid){

        Accounts account =new Accounts();
        account.setAccountId(accountid);
        Accounts a =accountService.withdraw(account,money,optype,transferid);
        return a;
    }
    @RequestMapping(value = "/transfer" ,method = RequestMethod.GET)
    public @ResponseBody Accounts transfer(Integer transferid,Integer accountid ,double money){
               Accounts ina=new Accounts();
        ina.setAccountId(transferid);
        Accounts out =new Accounts();
        out.setAccountId(accountid);
        Accounts a=accountService.transfer(ina,out,money);
        return a;
    }
    @RequestMapping(value = "/findAll" ,method = RequestMethod.GET)
    public @ResponseBody List<Accounts> FindAll(){
            List<Accounts>list=accountService.findAll();
        return list;
    }
}
