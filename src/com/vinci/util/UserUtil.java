package com.vinci.util;

import com.vinci.bean.Users;
import javax.servlet.http.HttpSession;

/**
 * @Author:Vinci_Ma
 * @Oescription:
 * @Date Created in 2020-08-21-18:14
 * @Modified By:
 */
public class UserUtil {
    public static String getUserName(HttpSession session){
        //根据session获取用户的姓名
        return (String) session.getAttribute("adminUserName");
    }
    public static String getUserPhone(HttpSession session){
        // TODO : 还没有编写柜子端,未存储任何的录入人信息
        return "18888888888";
    }

    public static String getLoginSms(HttpSession session,String userPhone){
        return (String) session.getAttribute(userPhone);
    }
    public static void setLoginSms(HttpSession session,String userPhone,String code){
        session.setAttribute(userPhone,code);
    }
    public static void setWxUser(HttpSession session, Users user){
        session.setAttribute("wxUser",user);
    }
    public static Users getWxUser(HttpSession session){
        return (Users) session.getAttribute("wxUser");
    }

}
