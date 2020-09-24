package com.vinci.dao;

import com.vinci.bean.Users;

import java.util.List;
import java.util.Map;

/**
 * @Author:Vinci_Ma
 * @Oescription:
 * @Date Created in 2020-08-26-17:37
 * @Modified By:
 */
public interface BaseUsersDao {
    /**
     * @Description
     * @param
     * @Return java.util.List<java.util.Map<java.lang.String,java.lang.Integer>>
     * @Author Vinci_Ma
     * @Date Created in 2020/8/25 22:17
     **/
    List<Map<String, Integer>> console();

    /**
     * @Description ���ڲ�ѯ���п��
     * @param limit �Ƿ��ҳ�ı��(true��ʾ��ҳ��false��ʾ��ѯ���п��)
     * @param offset SQL������ʼ����
     * @param pageNumber ҳ��ѯ������
     * @Return java.util.List<com.vinci.bean.Express>
     * @Author Vinci_Ma
     * @Date Created in 2020/8/21 16:43
     **/
    List<Users> findAll(boolean limit, int offset, int pageNumber);

    /**
     * @Description �����ֻ������ѯ�û���Ϣ
     * @param uphone �û��ֻ�����
     * @Return com.vinci.bean.Courier
     * @Author Vinci_Ma
     * @Date Created in 2020/8/24 0:30
     **/
    Users findByUsersPhone(String uphone);

    /**
     * @Description �����û���Ϣ
     * @param users �û���Ϣ
     * @Return boolean �����Ϣ�Ľ����true��ʾ�ɹ���false��ʾʧ��
     * @Author Vinci_Ma
     * @Date Created in 2020/8/24 0:35
     **/
    boolean inset(Users users);

    /**
     * @Description �����û����ֻ����������Ӧ�û�����������Ϣ�ĸ���
     * @param uphone �û����ֻ�����
     * @param newUsers �µ��û���Ϣ
     * @Return boolean �޸ĵĽ����true��ʾ�ɹ���false��ʾʧ��
     * @Author Vinci_Ma
     * @Date Created in 2020/8/24 0:36
     **/
    boolean update(String uphone,Users newUsers);

    /**
     * @Description �����û����ֻ������ҵ���Ӧ�û���Ϣ��������ɾ����������
     * @param id �û��ı��
     * @Return boolean ɾ���Ľ����true��ʾ�ɹ���false��ʾʧ��
     * @Author Vinci_Ma
     * @Date Created in 2020/8/24 0:38
     **/
    boolean delete(int id);
}
