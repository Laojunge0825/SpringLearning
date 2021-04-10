package com.yc.bean2;

import com.yc.springframework.stereotype.MyService;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-05 16:22
 */
@MyService
public class Session implements MySession {
    private String name = "张三";
    private Session session;
    public String getSession() {
        System.out.println("session");
        return "Session";
    }

    public Session(){
        System.out.println("Session构造......");
    }

    public Session(Session session) {
        this.session =session;

    }

    @Override
    public String toString() {
        return "Session:" + this.name;
    }
}
