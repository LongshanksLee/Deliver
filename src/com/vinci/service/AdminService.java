package com.vinci.service;

import com.vinci.dao.BaseAdminDao;
import com.vinci.dao.imp.AdminDaoMysql;

import java.util.Date;

/**
 * @Author:Vinci_Ma
 * @Oescription:
 * @Date Created in 2020-08-18-15:32
 * @Modified By:
 */
public class AdminService {
    private static BaseAdminDao dao = new AdminDaoMysql();
    /**
    * @Description 更新登陆时间与ip
    * @param username
    * @param date
    * @param ip
    * @Return void
    * @Author Vinci_Ma
    * @Date Created in 2020/8/18 15:36
    **/
    public static void updateLoginTimeAndIp(String username, Date date, String ip){
        dao.updateLoginTime(username,date,ip);
    }

    /**
    * @Description 登陆
    * @param username
    * @param password
    * @Return boolean true表示成功，false表示失败
    * @Author Vinci_Ma
    * @Date Created in 2020/8/18 15:36
    **/
    public static boolean login(String username,String password){
        return dao.login(username,password);
    }
}
