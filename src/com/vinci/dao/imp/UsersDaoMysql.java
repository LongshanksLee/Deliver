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
    //���ڲ�ѯ���ݿ��е����û�����ע����
    public static final String SQL_CONSOLE = "SELECT " +
            "COUNT(ID) data_size," +
            "COUNT(TO_DAYS(SIGNUPTIME)=TO_DAYS(NOW()) OR NULL) data_today" +
            " FROM USERS WHERE STATUS=0";
    //���ڲ�ѯ���ݿ��е������û���Ϣ
    public static final String SQL_FIND_ALL = "SELECT * FROM USERS WHERE STATUS=0";
    //���ڷ�ҳ��ѯ���ݿ���û���Ϣ
    public static final String SQL_FIND_LIMIT = "SELECT * FROM USERS WHERE STATUS=0 LIMIT ?,? ";
    //����ͨ���û��ֻ������ѯ�û���Ϣ
    public static final String SQL_FIND_BY_UPHONE = "SELECT * FROM USERS WHERE UPHONE=? AND STATUS=0";
    //¼���û���Ϣ
    public static final String SQL_INSERT = "INSERT INTO USERS (ID,UNAME,UPHONE,UIDCARD,UCODE,SIGNUPTIME,LOGINTIME,STATUS) VALUES(null,?,?,?,?,NOW(),NOW(),0)";
    //�޸��û���Ϣ
    public static final String SQL_UPDATE = "UPDATE USERS SET UNAME=?,UPHONE=?,UIDCARD=?,UCODE=? WHERE ID=?";
    //�û���Ϣ��ɾ��(���û���״̬��Ϊ1����Ϊɾ��)
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
     * @param limit      �Ƿ��ҳ�ı��(true��ʾ��ҳ��false��ʾ��ѯ���п��)
     * @param offset     SQL������ʼ����
     * @param pageNumber ҳ��ѯ������
     * @Description ���ڲ�ѯ���п��
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
                //3��������(��ѡ)
                state.setInt(1,offset);
                state.setInt(2,pageNumber);
            }else {
                state = conn.prepareStatement(SQL_FIND_ALL);
            }
            //4��ִ��SQL���
            result = state.executeQuery();
            //5����ȡִ�еĽ��
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
            //�ͷ���Դ
            DruidUtil.close(conn,state,result);
        }
//        System.out.println(data);
        return data;
    }

    /**
     * @param uphone �û��ֻ�����
     * @Description �����ֻ������ѯ�û���Ϣ
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
     * @param users �û���Ϣ
     * @Description �����û���Ϣ
     * @Return boolean �����Ϣ�Ľ����true��ʾ�ɹ���false��ʾʧ��
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
     * @param uphone   �û����ֻ�����
     * @param newUsers �µ��û���Ϣ
     * @Description �����û����ֻ����������Ӧ�û�����������Ϣ�ĸ���
     * @Return boolean �޸ĵĽ����true��ʾ�ɹ���false��ʾʧ��
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
     * @param id �û����ֻ�����
     * @Description �����û����ֻ������ҵ���Ӧ�û���Ϣ��������ɾ����������
     * @Return boolean ɾ���Ľ����true��ʾ�ɹ���false��ʾʧ��
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
