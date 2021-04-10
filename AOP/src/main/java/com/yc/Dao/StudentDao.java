package com.yc.Dao;

import org.springframework.stereotype.Repository;

/**
 * @program: Spring01
 * @author: 作者
 * @create: 2021-04-04 14:44
 */
@Repository
public interface StudentDao {
    public int add(String name);
    public void update(String name);
    public String  find(String name);
}
