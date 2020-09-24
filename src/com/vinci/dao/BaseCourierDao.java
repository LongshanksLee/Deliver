package com.vinci.dao;

import com.vinci.bean.Courier;
import com.vinci.bean.Express;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author:Vinci_Ma
 * @Oescription: 快递员管理
 * @Date Created in 2020-08-23-23:51
 * @Modified By:
 */
public interface BaseCourierDao {
    //根据用户的用户名更新时间
    void updateLoginTime(String username, Date date);

    /**
    * @Description
    * @param
    * @Return java.util.List<java.util.Map<java.lang.String,java.lang.Integer>>
    * @Author Vinci_Ma
    * @Date Created in 2020/8/25 22:17
    **/
    List<Map<String, Integer>> console();

    /**
     * @Description 用于查询所有快递
     * @param limit 是否分页的标记(true表示分页，false表示查询所有快递)
     * @param offset SQL语句的起始索引
     * @param pageNumber 页查询的数量
     * @Return java.util.List<com.vinci.bean.Express>
     * @Author Vinci_Ma
     * @Date Created in 2020/8/21 16:43
     **/
    List<Courier> findAll(boolean limit, int offset, int pageNumber);

    /**
    * @Description 根据手机号码查询快递员信息
    * @param courierphone 手机号码
    * @Return com.vinci.bean.Courier
    * @Author Vinci_Ma
    * @Date Created in 2020/8/24 0:30
    **/
    Courier findByCourierPhone(String courierphone);
    
    /**
    * @Description 增加快递员信息
    * @param c 快递员信息
    * @Return boolean 添加信息的结果，true表示成功，false表示失败
    * @Author Vinci_Ma
    * @Date Created in 2020/8/24 0:35
    **/
    boolean inset(Courier c);

    /**
    * @Description 根据快递员的手机号码查找相应快递员，并进行信息的更改
    * @param courierphone 快递员的手机号码
    * @param newCourier 新的快递员信息
    * @Return boolean 修改的结果，true表示成功，false表示失败
    * @Author Vinci_Ma
    * @Date Created in 2020/8/24 0:36
    **/
    boolean update(String courierphone,Courier newCourier);

    /**
    * @Description 跟库快递员的手机号码找到相应快递员信息，并进行删除或者重置
    * @param id 编号
    * @Return boolean 删除的结果，true表示成功，false表示失败
    * @Author Vinci_Ma
    * @Date Created in 2020/8/24 0:38
    **/
    boolean delete(int id);
}
