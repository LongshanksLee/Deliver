package com.vinci.dao;

import com.vinci.bean.Express;
import com.vinci.exception.DuplicateCodeException;

import java.util.List;
import java.util.Map;

/**
 * @Author:Vinci_Ma
 * @Oescription: 快递管理
 * @Date Created in 2020-08-21-16:41
 * @Modified By:
 */
public interface BaseExpressDao {
    /**
    * @Description 控制台，
     *  用于查询数据库中的全部快递（总数+新增），待取件快递（总数+新增）
    * @param
    * @Return java.util.List<java.util.Map<java.lang.String,java.lang.Integer>>
    * @Author Vinci_Ma
    * @Date Created in 2020/8/21 16:42
    **/
    List<Map<String,Integer>> console();

    /**
    * @Description 用于查询所有快递
    * @param limit 是否分页的标记(true表示分页，false表示查询所有快递)
    * @param offset SQL语句的起始索引
    * @param pageNumber 页查询的数量
    * @Return java.util.List<com.vinci.bean.Express>
    * @Author Vinci_Ma
    * @Date Created in 2020/8/21 16:43
    **/
    List<Express> findAll(boolean limit, int offset, int pageNumber);

    /**
    * @Description  根据快递单号，查询快递信息
    * @param number 快递单号
    * @Return com.vinci.bean.Express 快递信息，单号不存在时返回null
    * @Author Vinci_Ma
    * @Date Created in 2020/8/21 16:45
    **/
    Express findByNumber(String number);

    /**
    * @Description 根据快递取件码，查询快递信息
    * @param code 取件码
    * @Return com.vinci.bean.Express
    * @Author Vinci_Ma
    * @Date Created in 2020/8/21 16:47
    **/
    Express findByCode(String code);

    /**
    * @Description 根据用户手机号码，查询他所有的快递信息
    * @param userPhone 用户手机号码
    * @Return java.util.List<com.vinci.bean.Express>
    * @Author Vinci_Ma
    * @Date Created in 2020/8/21 16:47
    **/
    List<Express> findByUserPhone(String userPhone);

    /**
     * @Description 根据用户手机号码，查询他所有的快递信息
     * @param userPhone 用户手机号码
     * @Return java.util.List<com.vinci.bean.Express>
     * @Author Vinci_Ma
     * @Date Created in 2020/8/21 16:47
     **/
    List<Express> findByUserPhoneAndStatus(String userPhone,int status);

    /**
    * @Description 根据录入人手机号码，查询录入的所有记录
    * @param sysPhone 录入人手机号码
    * @Return java.util.List<com.vinci.bean.Express> 查询的快递信息列表
    * @Author Vinci_Ma
    * @Date Created in 2020/8/21 16:48
    **/
    List<Express> findBySysPhone(String sysPhone);

    /**
    * @Description 快递录入
    * @param e 需要录入的快递对象
    * @Return boolean 录入的结果，true表示成功，false表示失败
    * @Author Vinci_Ma
    * @Date Created in 2020/8/21 16:48
    **/
    boolean insert(Express e) throws DuplicateCodeException;

    /**
    * @Description 快递的修改
    * @param id 要修改的快递id
    * @param newExpress 新的快递对象（number，company,username,userPhone）
    * @Return boolean 修改的结果，true表示成功，false表示失败
    * @Author Vinci_Ma
    * @Date Created in 2020/8/21 16:49
    **/
    boolean update(int id,Express newExpress);

    /**
    * @Description 更改快递的状态为1，表示取件完成
    * @param code 要修改的快递取件码
    * @Return boolean 修改的结果，true表示成功，false表示失败
    * @Author Vinci_Ma
    * @Date Created in 2020/8/21 16:50
    **/
    boolean updateStatus(String code);

    /**
    * @Description 根据id，删除单个快递信息
    * @param id 待删除的快递单号id
    * @Return boolean 删除的结果，true表示成功，false表示失败
    * @Author Vinci_Ma
    * @Date Created in 2020/8/21 16:51
    **/
    boolean delete(int id);
}
