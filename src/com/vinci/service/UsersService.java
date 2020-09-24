package com.vinci.service;

import com.vinci.bean.Users;
import com.vinci.dao.BaseUsersDao;
import com.vinci.dao.imp.UsersDaoMysql;

import java.util.List;
import java.util.Map;

/**
 * @Author:Vinci_Ma
 * @Oescription:
 * @Date Created in 2020-08-26-17:38
 * @Modified By:
 */
public class UsersService{
    private static BaseUsersDao dao = new UsersDaoMysql();
    /**
     * @Description
     * @Return java.util.List<java.util.Map < java.lang.String, java.lang.Integer>>
     * @Author Vinci_Ma
     * @Date Created in 2020/8/25 22:17
     **/
    public static List<Map<String, Integer>> console() {
        return dao.console();
    }

    /**
     * @param limit      是否分页的标记(true表示分页，false表示查询所有快递)
     * @param offset     SQL语句的起始索引
     * @param pageNumber 页查询的数量
     * @Description 用于查询所有快递
     * @Return java.util.List<com.vinci.bean.Express>
     * @Author Vinci_Ma
     * @Date Created in 2020/8/21 16:43
     **/
    public static List<Users> findAll(boolean limit, int offset, int pageNumber) {
        return dao.findAll(limit, offset, pageNumber);
    }

    /**
     * @param uphone 用户手机号码
     * @Description 根据手机号码查询用户信息
     * @Return com.vinci.bean.Courier
     * @Author Vinci_Ma
     * @Date Created in 2020/8/24 0:30
     **/
    public static Users findByUsersPhone(String uphone) {
        return dao.findByUsersPhone(uphone);
    }

    /**
     * @param users 用户信息
     * @Description 增加用户信息
     * @Return boolean 添加信息的结果，true表示成功，false表示失败
     * @Author Vinci_Ma
     * @Date Created in 2020/8/24 0:35
     **/
    public static boolean inset(Users users) {
        return dao.inset(users);
    }

    /**
     * @param uphone   用户的手机号码
     * @param newUsers 新的用户信息
     * @Description 根据用户的手机号码查找相应用户，并进行信息的更改
     * @Return boolean 修改的结果，true表示成功，false表示失败
     * @Author Vinci_Ma
     * @Date Created in 2020/8/24 0:36
     **/
    public static boolean update(String uphone, Users newUsers) {
        return dao.update(uphone, newUsers);
    }

    /**
     * @param id 用户的编号
     * @Description 根据用户的手机号码找到相应用户信息，并进行删除或者重置
     * @Return boolean 删除的结果，true表示成功，false表示失败
     * @Author Vinci_Ma
     * @Date Created in 2020/8/24 0:38
     **/
    public static boolean delete(int id) {
        return dao.delete(id);
    }
}
