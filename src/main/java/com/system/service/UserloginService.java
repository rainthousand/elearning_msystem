package com.system.service;

import com.system.entity.Userlogin;

import java.util.List;

/**
 *
 *
 */
public interface UserloginService {

    //根据名字查找用户
    Userlogin findByName(String name) throws Exception;

    //保存用户登录信息
    void save(Userlogin userlogin) throws Exception;

    //根据姓名删除
    void removeByName(String name) throws Exception;

    //根据用户名更新
    void updateByName(String name, Userlogin userlogin);

    //新增：查找所有用户
    List<Userlogin> findAll() throws Exception;
}
