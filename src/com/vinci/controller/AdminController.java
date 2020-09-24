package com.vinci.controller;

import com.vinci.bean.Message;
import com.vinci.mvc.ResponseBody;
import com.vinci.service.AdminService;
import com.vinci.util.JSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Author:Vinci_Ma
 * @Oescription: ��¼ҳ�����
 * @Date Created in 2020-08-18-15:48
 * @Modified By:
 */
public class AdminController {
    //����ĵ�ַajax
    @ResponseBody("/admin/login.do")
    public String login(HttpServletRequest request, HttpServletResponse response){
        //1�����ղ���
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//        System.out.println(username+" "+password);
        //2������service������������ȡ���
        boolean result = AdminService.login(username, password);//JSON���{status:0,result:"��½�ɹ�"��}
        //3�����ݽ�������ز�ͬ�����ݸ�ajax
        Message msg = null;
        if (result){
            msg = new Message(0,"��½�ɹ���");
            //��½ʱ���Լ�ip�ĸ���
            Date date = new Date();
            String ip = request.getRemoteAddr();//����ʱʹ�õ��Ǳ���ip���ܶ�0
            AdminService.updateLoginTimeAndIp(username,date,ip);
            request.getSession().setAttribute("adminUserName","username");
        }else {
            msg = new Message(-1,"��¼ʧ�ܣ�");
        }
        //4��������ת��ΪJSON
        String json = JSONUtil.toJSON(msg);
//        System.out.println(json);
        //5����JSON���ظ�ajax
        return json;
    }
}
