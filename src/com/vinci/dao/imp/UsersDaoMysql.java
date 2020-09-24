package com.vinci.dao.imp;

import com.vinci.bean.Users;
import com.vinci.dao.BaseUsersDao;
import com.vinci.util.DruidUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:Vinci_Ma
 * @Oescription:
 * @Date Created in 2020-08-26-17:44
 * @Modified By:
 */
public class UsersDaoMysql implements BaseUsersDao {
    //用于查询数据库中的总用户，日注册量
    public static final String SQL_CONSOLE = "SELECT " +
            "COUNT(ID) data_size," +
            "COUNT(TO_DAYS(SIGNUPTIME)=TO_DAYS(NOW()) OR NULL) data_today" +
            " FROM USERS WHERE STATUS=0";
    //用于查询数据库中的所有用户信息
    public static final String SQL_FIND_ALL = "SELECT * FROM USERS WHERE STATUS=0";
    //用于分页查询数据库的用户信息
    public static final String SQL_FIND_LIMIT = "SELECT * FROM USERS WHERE STATUS=0 LIMIT ?,? ";
    //用于通过用户手机号码查询用户信息
    public static final String SQL_FIND_BY_UPHONE = "SELECT * FROM USERS WHERE UPHONE=? AND STATUS=0";
    //录入用户信息
    public static final String SQL_INSERT = "INSERT INTO USERS (ID,UNAME,UPHONE,UIDCARD,UCODE,SIGNUPTIME,LOGINTIME,STATUS) VALUES(null,?,?,?,?,NOW(),NOW(),0)";
    //修改用户信息
    public static final String SQL_UPDATE = "UPDATE USERS SET UNAME=?,UPHONE=?,UIDCARD=?,UCODE=? WHERE ID=?";
    //用户信息的删除(将用户的状态变为1，即为删除)
    public static final String SQL_DELETE = "UPDATE USERS SET STATUS=1 WHERE ID=?";
    /**
     * @Description
     * @Return java.util.List<java.util.Map < java.lang.String, java.lang.Integer>>
     * @Author Vinci_Ma
     * @Date Created in 2020/8/25 22:17
     **/
    @Override
    public List<Map<String, Integer>> console() {
        ArrayList<Map<String,Integer>> data = new ArrayList<>();
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            state = conn.prepareStatement(SQL_CONSOLE);
            result = state.executeQuery();
            if (result.next()) {
                int data_size = result.getInt("data_size");
                int data_today = result.getInt("data_today");
                Map data1 = new HashMap();
                data1.put("data_size", data_size);
                data1.put("data_today", data_today);
                data.add(data1);
                System.out.println(data_size);
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
    public List<Users> findAll(boolean limit, int offset, int pageNumber) {
        ArrayList<Users> data = new ArrayList<>();
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        ResultSet result = null;
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
                String uname = result.getString("uname");
                String uphone = result.getString("uphone");
                String uidcard = result.getString("uidcard");
                String ucode = result.getString("ucode");
                Timestamp signuptime = result.getTimestamp("signuptime");
                Timestamp logintime = result.getTimestamp("logintime");
                int status = result.getInt("status");
                Users users = new Users(id,uname,uphone,uidcard,ucode,signuptime,logintime,status);
                data.add(users);
//                System.out.println(users+"   UsersMySql  findAll");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            //释放资源
            DruidUtil.close(conn,state,result);
        }
//        System.out.println(data);
        return data;
    }

    /**
     * @param uphone 用户手机号码
     * @Description 根据手机号码查询用户信息
     * @Return com.vinci.bean.Courier
     * @Author Vinci_Ma
     * @Date Created in 2020/8/24 0:30
     **/
    @Override
    public Users findByUsersPhone(String uphone) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        ResultSet result = null;
        try {
            state = conn.prepareStatement(SQL_FIND_BY_UPHONE);
            state.setString(1,uphone);
            result = state.executeQuery();
            while (result.next()){
                int id = result.getInt("id");
                String uname = result.getString("uname");
                String uidcard = result.getString("uidcard");
                String ucode = result.getString("ucode");
                Timestamp signuptime = result.getTimestamp("signuptime");
                Timestamp logintime = result.getTimestamp("logintime");
                int status = result.getInt("status");
                Users users = new Users(id,uname,uphone,uidcard,ucode,signuptime,logintime,status);
                return users;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,result);
        }
        return null;
    }

    /**
     * @param users 用户信息
     * @Description 增加用户信息
     * @Return boolean 添加信息的结果，true表示成功，false表示失败
     * @Author Vinci_Ma
     * @Date Created in 2020/8/24 0:35
     **/
    @Override
    public boolean inset(Users users) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        try {
            state = conn.prepareStatement(SQL_INSERT);
            state.setString(1,users.getUname());
            state.setString(2,users.getUphone());
            state.setString(3, users.getUidcard());
            state.setString(4, users.getUcode());
            return state.executeUpdate()>0?true:false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,null);
        }
        return false;
    }

    /**
     * @param uphone   用户的手机号码
     * @param newUsers 新的用户信息
     * @Description 根据用户的手机号码查找相应用户，并进行信息的更改
     * @Return boolean 修改的结果，true表示成功，false表示失败
     * @Author Vinci_Ma
     * @Date Created in 2020/8/24 0:36
     **/
    @Override
    public boolean update(String uphone, Users newUsers) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        try {
            state = conn.prepareStatement(SQL_UPDATE);
            state.setString(1,newUsers.getUname());
            state.setString(2,newUsers.getUphone());
            state.setString(3, newUsers.getUidcard());
            state.setString(4, newUsers.getUcode());
            state.setInt(5,newUsers.getId());
            return state.executeUpdate()>0?true:false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,null);
        }
        return false;
    }

    /**
     * @param id 用户的手机号码
     * @Description 根据用户的手机号码找到相应用户信息，并进行删除或者重置
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
