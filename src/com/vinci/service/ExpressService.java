package com.vinci.service;

import com.vinci.bean.Express;
import com.vinci.dao.BaseExpressDao;
import com.vinci.dao.imp.ExpressDaoMysql;
import com.vinci.exception.DuplicateCodeException;
import com.vinci.util.RandomUtil;
import com.vinci.util.SMSUtil;

import java.util.List;
import java.util.Map;

/**
 * @Author:Vinci_Ma
 * @Oescription:
 * @Date Created in 2020-08-21-17:25
 * @Modified By:
 */
public class ExpressService {
    private static BaseExpressDao dao = new ExpressDaoMysql();

    /**
     * @Description 控制台，
     * 用于查询数据库中的全部快递（总数+新增），待取件快递（总数+新增）
     * @Return java.util.List<java.util.Map < java.lang.String, java.lang.Integer>>
     * @Author Vinci_Ma
     * @Date Created in 2020/8/21 16:42
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
    public static List<Express> findAll(boolean limit, int offset, int pageNumber) {
        return dao.findAll(limit, offset, pageNumber);
    }

    /**
     * @param number 快递单号
     * @Description 根据快递单号，查询快递信息
     * @Return com.vinci.bean.Express 快递信息，单号不存在时返回null
     * @Author Vinci_Ma
     * @Date Created in 2020/8/21 16:45
     **/
    public static Express findByNumber(String number) {
        return dao.findByNumber(number);
    }

    /**
     * @param code 取件码
     * @Description 根据快递取件码，查询快递信息
     * @Return com.vinci.bean.Express
     * @Author Vinci_Ma
     * @Date Created in 2020/8/21 16:47
     **/
    public static Express findByCode(String code) {
        return dao.findByCode(code);
    }

    /**
     * @param userPhone 用户手机号码
     * @Description 根据用户手机号码，查询他所有的快递信息
     * @Return java.util.List<com.vinci.bean.Express>
     * @Author Vinci_Ma
     * @Date Created in 2020/8/21 16:47
     **/
    public static List<Express> findByUserPhone(String userPhone) {
        return dao.findByUserPhone(userPhone);
    }

    /**
     * 根据用户手机号码，查询他所有的快递信息
     *
     * @param userPhone 手机号码
     * @param status 状态码
     * @return 查询的快递信息列表
     */
    public static List<Express> findByUserPhoneAndStatus(String userPhone,int status) {
        return dao.findByUserPhoneAndStatus(userPhone,status);
    }

    /**
     * @param sysPhone 录入人手机号码
     * @Description 根据录入人手机号码，查询录入的所有记录
     * @Return java.util.List<com.vinci.bean.Express> 查询的快递信息列表
     * @Author Vinci_Ma
     * @Date Created in 2020/8/21 16:48
     **/
    public static List<Express> findBySysPhone(String sysPhone) {
        return dao.findBySysPhone(sysPhone);
    }

    /**
     * @param e 需要录入的快递对象
     * @Description 快递录入
     * @Return boolean 录入的结果，true表示成功，false表示失败
     * @Author Vinci_Ma
     * @Date Created in 2020/8/21 16:48
     **/
    public static boolean insert(Express e){
        //生成取件码
        e.setCode(RandomUtil.getcode()+"");
        //进行数据插入
        try{
            boolean flag = dao.insert(e);
            if(flag){
                //录入成功，发送短信验证码
                SMSUtil.send(e.getUserPhone(),e.getCode());
            }
            return flag;
        }catch (DuplicateCodeException duplicateCodeException){
            //通过递归解决取件码重复的问题
            return insert(e);
        }
    }

    /**
     * @param id         要修改的快递id
     * @param newExpress 新的快递对象（number，company,username,userPhone）
     * @Description 快递的修改
     * @Return boolean 修改的结果，true表示成功，false表示失败
     * @Author Vinci_Ma
     * @Date Created in 2020/8/21 16:49
     **/
    public static boolean update(int id, Express newExpress) {
        if(newExpress.getUserPhone()!=null){
            dao.delete(id);
            return insert(newExpress);
        }else{
            //先对信息进行修改
            boolean update = dao.update(id, newExpress);
            //对快递信息进行查询
            Express e = dao.findByNumber(newExpress.getNumber());
            if(newExpress.getStatus() == 1){
                //做了一个取件操作
                updateStatus(e.getCode());
            }
            return update;
        }
    }

    /**
     * @param code 要修改的快递取件码
     * @Description 更改快递的状态为1，表示取件完成
     * @Return boolean 修改的结果，true表示成功，false表示失败
     * @Author Vinci_Ma
     * @Date Created in 2020/8/21 16:50
     **/
    public static boolean updateStatus(String code) {
        return dao.updateStatus(code);
    }

    /**
     * @param id 待删除的快递单号id
     * @Description 根据id，删除单个快递信息
     * @Return boolean 删除的结果，true表示成功，false表示失败
     * @Author Vinci_Ma
     * @Date Created in 2020/8/21 16:51
     **/
    public static boolean delete(int id) {
        return dao.delete(id);
    }
}
