package com.vinci.dao.imp;

import com.vinci.bean.Courier;
import com.vinci.dao.BaseCourierDao;
import com.vinci.util.DruidUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:Vinci_Ma
 * @Oescription:
 * @Date Created in 2020-08-24-0:45
 * @Modified By:
 */
public class CourierDaoMysql implements BaseCourierDao {
    //用于查询主页显示的快递员人数和日注册量
    public static final String SQL_CONSOLE = "SELECT " +
            "COUNT(ID) data_size," +
            "COUNT(TO_DAYS(SIGNUPTIME)=TO_DAYS(NOW()) OR NULL) data_today" +
            " FROM COURIER";
    //用于查询每个快递员的派件数
    public static final String SQL_EXPRESSCOUNT = "SELECT courier.id,COUNT(courier.id) as count " +
            "FROM courier" +
            "LEFT JOIN express ON courier.courierphone=express.sysPhone " +
            "GROUP BY courier.id";
    //用于查询数据库中的所有快递员信息
    public static final String SQL_FIND_ALL = "SELECT * FROM COURIER";
    //用于分页查询数据库中的快递信息
    public static final String SQL_FIND_LIMIT = "SELECT * FROM COURIER LIMIT ?,?";
    //通过手机号查询快递员信息
    public static final String SQL_FIND_BY_COURIERPHONE = "SELECT * FROM COURIER WHERE COURIERPHONE=?";
    //录入快递员信息
    public static final String SQL_INSERT = "INSERT INTO COURIER (ID,COURIERNAME,COURIERPHONE,IDCARD,CODE,SIGNUPTIME,LOGINTIME) VALUES(null,?,?,?,?,NOW(),NOW())";
    //快递员信息修改
    public static final String SQL_UPDATE = "UPDATE COURIER SET COURIERNAME=?,COURIERPHONE=?,IDCARD=?,CODE=? WHERE ID=?";
    //快递员信息的删除
    public static final String SQL_DELETE = "DELETE FROM COURIER WHERE ID=?";
    //管理员登录时间的更新
    private static final String SQL_UPDATE_LOGIN_TIME = "UPDATE COURIER SET LOGINTIME=? WHERE COURIERNAME=?";


    @Override
    public void updateLoginTime(String couriername, Date date) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        try {
            state = conn.prepareStatement(SQL_UPDATE_LOGIN_TIME);
            state.setDate(1,new java.sql.Date(date.getTime()));
            state.setString(2,couriername);
            state.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            //5、释放资源
            DruidUtil.close(conn,state,null);
        }
    }

    /**
     * @Description 用于查询数据库中每个人有多少快递
     * @Return java.util.List<java.util.Map < java.lang.String, java.lang.Integer>>
     * @Author Vinci_Ma
     * @Date Created in 2020/8/25 22:17
     **/
    @Override
    public List<Map<String, Integer>> console() {
        ArrayList<Map<String,Integer>> data = new ArrayList<>();
        //1.    获取数据库的连接
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        ResultSet result = null;
        //2.    预编译SQL语句
        try {
            state = conn.prepareStatement(SQL_CONSOLE);
            result = state.executeQuery();
            if (result.next()){
                int data_size = result.getInt("data_size");
                int data_today = result.getInt("data_today");
                Map data1 = new HashMap();
                data1.put("data_size",data_size);
                data1.put("data_today",data_today);
                data.add(data1);
//                System.out.println(data.size());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,result);
        }
        return data;
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
    @Override
    public List<Courier> findAll(boolean limit, int offset, int pageNumber) {
        ArrayList<Courier> data = new ArrayList<>();
        //1、获取数据库的连接
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        ResultSet result = null;
        //2、预编译SQL语句
        try {
            if(limit) {
                state = conn.prepareStatement(SQL_FIND_LIMIT);
                //3、填充参数(可选)
                state.setInt(1,offset);
                state.setInt(2,pageNumber);
            }else {
                state = conn.prepareStatement(SQL_FIND_ALL);
            }
            //4、执行SQL语句
            result = state.executeQuery();
            //5、获取执行的结果
            while (result.next()){
                int id = result.getInt("id");
                String couriername = result.getString("couriername");
                String courierphone = result.getString("courierphone");
                String idcard = result.getString("idcard");
                String code = result.getString("code");
                Timestamp signuptime = result.getTimestamp("signuptime");
                Timestamp logintime = result.getTimestamp("logintime");
                Courier courier = new Courier(id,couriername,courierphone,idcard,code,signuptime,logintime);
                data.add(courier);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            //释放资源
            DruidUtil.close(conn,state,result);
        }
        return data;
    }

    /**
     * @param courierphone 手机号码
     * @Description 根据手机号码查询快递员信息
     * @Return com.vinci.bean.Courier
     * @Author Vinci_Ma
     * @Date Created in 2020/8/24 0:30
     **/
    @Override
    public Courier findByCourierPhone(String courierphone) {
        //1、获取数据库的连接
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        ResultSet result = null;
        //2、预编译SQL语句
        try {
            state = conn.prepareStatement(SQL_FIND_BY_COURIERPHONE);
            //3、填充参数(可选)
            state.setString(1,courierphone);
            //4、执行SQL语句
            result = state.executeQuery();
            //5、获取执行的结果
            while (result.next()){
                int id = result.getInt("id");
                String couriername = result.getString("couriername");
                String idcard = result.getString("idcard");
                String code = result.getString("code");
                Timestamp signuptime = result.getTimestamp("signuptime");
                Timestamp logintime = result.getTimestamp("logintime");
                Courier courier = new Courier(id,couriername,courierphone,idcard,code,signuptime,logintime);
                return courier;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            //释放资源
            DruidUtil.close(conn,state,result);
        }
        return null;
    }

    /**
     * @param c 快递员信息
     * @Description 增加快递员信息
     * @Return boolean 添加信息的结果，true表示成功，false表示失败
     * @Author Vinci_Ma
     * @Date Created in 2020/8/24 0:35
     **/
    @Override
    public boolean inset(Courier c) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        try {
            state = conn.prepareStatement(SQL_INSERT);
            state.setString(1,c.getCouriername());
            state.setString(2,c.getCourierphone());
            state.setString(3,c.getIdcard());
            state.setString(4,c.getCode());
            return state.executeUpdate()>0?true:false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,null);
        }
        return false;
    }

    /**
     * @param courierphone 快递员的手机号码
     * @param newCourier  新的快递员信息
     * @Description 根据快递员的手机号码查找相应快递员，并进行信息的更改
     * @Return boolean 修改的结果，true表示成功，false表示失败
     * @Author Vinci_Ma
     * @Date Created in 2020/8/24 0:36
     **/
    @Override
    public boolean update(String courierphone, Courier newCourier) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        try {
            state = conn.prepareStatement(SQL_UPDATE);
            state.setString(1,newCourier.getCouriername());
            state.setString(2,newCourier.getCourierphone());
            state.setString(3,newCourier.getIdcard());
            state.setString(4,newCourier.getCode());
            state.setInt(5,newCourier.getId());
            return state.executeUpdate()>0?true:false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,null);
        }
        return false;
    }

    /**
     * @param id 快递员的手机号码
     * @Description 根据快递员的手机号码找到相应快递员信息，并进行删除或者重置
     * @Return boolean 删除的结果，true表示成功，false表示失败
     * @Author Vinci_Ma
     * @Date Created in 2020/8/24 0:38
     **/
    @Override
    public boolean delete(int id) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        try {
            state = conn.prepareStatement(SQL_DELETE);
            state.setInt(1,id);
            return state.executeUpdate()>0?true:false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,null);
        }
        return false;
    }
}
