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
 * @Oescription: 登录页面控制
 * @Date Created in 2020-08-18-15:48
 * @Modified By:
 */
public class AdminController {
    //请求的地址ajax
    @ResponseBody("/admin/login.do")
    public String login(HttpServletRequest request, HttpServletResponse response){
        //1、接收参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//        System.out.println(username+" "+password);
        //2、利用service传参数，并获取结果
        boolean result = AdminService.login(username, password);//JSON结果{status:0,result:"登陆成功"，}
        //3、根据结果，返回不同的数据给ajax
        Message msg = null;
        if (result){
            msg = new Message(0,"登陆成功！");
            //登陆时间以及ip的更新
            Date date = new Date();
            String ip = request.getRemoteAddr();//测试时使用的是本机ip，很多0
            AdminService.updateLoginTimeAndIp(username,date,ip);
            request.getSession().setAttribute("adminUserName","username");
        }else {
            msg = new Message(-1,"登录失败！");
        }
        //4、将数据转换为JSON
        String json = JSONUtil.toJSON(msg);
//        System.out.println(json);
        //5、将JSON返回给ajax
        return json;
    }
}
