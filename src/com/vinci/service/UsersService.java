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
     * @param limit      �Ƿ��ҳ�ı��(true��ʾ��ҳ��false��ʾ��ѯ���п��)
     * @param offset     SQL������ʼ����
     * @param pageNumber ҳ��ѯ������
     * @Description ���ڲ�ѯ���п��
     * @Return java.util.List<com.vinci.bean.Express>
     * @Author Vinci_Ma
     * @Date Created in 2020/8/21 16:43
     **/
    public static List<Users> findAll(boolean limit, int offset, int pageNumber) {
        return dao.findAll(limit, offset, pageNumber);
    }

    /**
     * @param uphone �û��ֻ�����
     * @Description �����ֻ������ѯ�û���Ϣ
     * @Return com.vinci.bean.Courier
     * @Author Vinci_Ma
     * @Date Created in 2020/8/24 0:30
     **/
    public static Users findByUsersPhone(String uphone) {
        return dao.findByUsersPhone(uphone);
    }

    /**
     * @param users �û���Ϣ
     * @Description �����û���Ϣ
     * @Return boolean �����Ϣ�Ľ����true��ʾ�ɹ���false��ʾʧ��
     * @Author Vinci_Ma
     * @Date Created in 2020/8/24 0:35
     **/
    public static boolean inset(Users users) {
        return dao.inset(users);
    }

    /**
     * @param uphone   �û����ֻ�����
     * @param newUsers �µ��û���Ϣ
     * @Description �����û����ֻ����������Ӧ�û�����������Ϣ�ĸ���
     * @Return boolean �޸ĵĽ����true��ʾ�ɹ���false��ʾʧ��
     * @Author Vinci_Ma
     * @Date Created in 2020/8/24 0:36
     **/
    public static boolean update(String uphone, Users newUsers) {
        return dao.update(uphone, newUsers);
    }

    /**
     * @param id �û��ı��
     * @Description �����û����ֻ������ҵ���Ӧ�û���Ϣ��������ɾ����������
     * @Return boolean ɾ���Ľ����true��ʾ�ɹ���false��ʾʧ��
     * @Author Vinci_Ma
     * @Date Created in 2020/8/24 0:38
     **/
    public static boolean delete(int id) {
        return dao.delete(id);
    }
}
