package com.vinci.dao.imp;

import com.vinci.dao.BaseAdminDao;
import com.vinci.util.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * @Author:Vinci_Ma
 * @Oescription:
 * @Date Created in 2020-08-18-10:58
 * @Modified By:
 */
public class AdminDaoMysql implements BaseAdminDao {
    private static final String SQL_UPDATE_LOGIN_TIME = "UPDATE EADMIN SET LOGINTIME=?,LOGINIP=? WHERE USERNAME=?";
    private static final String SQL_LOGIN = "SELECT ID from EADMIN WHERE USERNAME=? AND PASSWORD=?";

    @Override
    public void updateLoginTime(String username, Date date, String ip) {
        //1、获取连接
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        //2、预编译sql语句
        try {
            state = conn.prepareStatement(SQL_UPDATE_LOGIN_TIME);
            //3、填充参数
            state.setDate(1,new java.sql.Date(date.getTime()));
            state.setString(2,ip);
            state.setString(3,username);
            //4、执行
            state.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            //5、释放资源
            DruidUtil.close(conn,state,null);
        }
    }

    @Override
    public boolean login(String username, String password) {
        //1、获取连接
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        ResultSet rs = null;
        //2、预编译sql语句
        try {
            state = conn.prepareStatement(SQL_LOGIN);
            //3、填充参数
            state.setString(1,username);
            state.setString(2,password);
            //4、执行
            rs = state.executeQuery();
            //5、根据查询结果返回
//            System.out.println(rs.next());
            return rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            //6、释放资源
            DruidUtil.close(conn,state,rs);
        }
        return false;
    }
}
