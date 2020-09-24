package com.vinci.service;

import com.vinci.bean.Courier;
import com.vinci.dao.BaseCourierDao;
import com.vinci.dao.imp.CourierDaoMysql;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author:Vinci_Ma
 * @Oescription:
 * @Date Created in 2020-08-24-2:07
 * @Modified By:
 */
public class CourierService{
    private static BaseCourierDao dao = new CourierDaoMysql();

    /**
    * @Description  用于查询数据库中的全部快递（总数+新增），待取件快递（总数+新增）
    * @param
    * @Return java.util.List<java.util.Map<java.lang.String,java.lang.Integer>>
    * @Author Vinci_Ma
    * @Date Created in 2020/8/25 22:15
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
    public static List<Courier> findAll(boolean limit, int offset, int pageNumber) {
        return dao.findAll(limit, offset, pageNumber);
    }

    /**
     * @param courierphone 手机号码
     * @Description 根据手机号码查询快递员信息
     * @Return com.vinci.bean.Courier
     * @Author Vinci_Ma
     * @Date Created in 2020/8/24 0:30
     **/
    public static Courier findByCourierPhone(String courierphone) {
        return dao.findByCourierPhone(courierphone);
    }

    /**
     * @param c 快递员信息
     * @Description 增加快递员信息
     * @Return boolean 添加信息的结果，true表示成功，false表示失败
     * @Author Vinci_Ma
     * @Date Created in 2020/8/24 0:35
     **/
    public static boolean inset(Courier c) {
        return dao.inset(c);
    }

    /**
     * @param courierphone 快递员的手机号码
     * @param newCourier   新的快递员信息
     * @Description 根据快递员的手机号码查找相应快递员，并进行信息的更改
     * @Return boolean 修改的结果，true表示成功，false表示失败
     * @Author Vinci_Ma
     * @Date Created in 2020/8/24 0:36
     **/
    public static boolean update(String courierphone, Courier newCourier) {
        return dao.update(courierphone, newCourier);
    }

    /**
    * @Description 更新登陆时间
    * @param couriername
    * @param date
    * @Return void
    * @Author Vinci_Ma
    * @Date Created in 2020/8/29 21:57
    **/
    public static void updateLoginTime(String couriername, Date date){
        dao.updateLoginTime(couriername,date);
    }

    /**
     * @param id 快递员的编号
     * @Description 跟库快递员的手机号码找到相应快递员信息，并进行删除或者重置
     * @Return boolean 删除的结果，true表示成功，false表示失败
     * @Author Vinci_Ma
     * @Date Created in 2020/8/24 0:38
     **/
    public static boolean delete(int id) {
        return dao.delete(id);
    }
}
