package com.vinci.dao;

import java.util.Date;

/**
 * @Author:Vinci_Ma
 * @Oescription: 用于定义eadmin表格的操作规范，管理员登录管理
 * @Date Created in 2020-08-18-10:53
 * @Modified By:
 */
public interface BaseAdminDao {
    //根据管理员的用户名更新时间和登录ip
    void updateLoginTime(String username,Date date,String ip);
    //管理员根据账号密码进行登录，true表示登陆成功
    boolean login(String username,String password);
}
