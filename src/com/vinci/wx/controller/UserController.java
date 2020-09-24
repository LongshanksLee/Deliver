package com.vinci.wx.controller;

import com.vinci.bean.Courier;
import com.vinci.bean.Message;
import com.vinci.bean.Users;
import com.vinci.mvc.ResponseBody;
import com.vinci.service.CourierService;
import com.vinci.util.JSONUtil;
import com.vinci.util.RandomUtil;
import com.vinci.util.SMSUtil;
import com.vinci.util.UserUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserController {

    @ResponseBody("/wx/loginSms.do")
    public String sendSms(HttpServletRequest request, HttpServletResponse response){
        String userPhone = request.getParameter("userPhone");
        //�õ��ֻ��ţ����û����Ͷ���
        String code = RandomUtil.getcode()+"";
        boolean flag = SMSUtil.loginSMS(userPhone, code);
//        String code = "123456";
        //�趨���ͳɹ�
//        boolean flag = true;
        Message msg = new Message();
        if(flag){
            //���ŷ��ͳɹ�
            msg.setStatus(0);
            msg.setResult("��֤���ѷ���,�����!");
        }else{
            //���ŷ���ʧ��
            msg.setStatus(1);
            msg.setResult("��֤�뷢��ʧ��,�����ֻ��Ż��Ժ�����");
        }
        UserUtil.setLoginSms(request.getSession(),userPhone,code);

        String json = JSONUtil.toJSON(msg);
        return json;
    }
    @ResponseBody("/wx/login.do")
    public String login(HttpServletRequest request, HttpServletResponse response){
        String userPhone = request.getParameter("userPhone");
        String userCode = request.getParameter("code");
        //ϵͳ���͵�
        String sysCode = UserUtil.getLoginSms(request.getSession(), userPhone);
        Message msg = new Message();
        if(sysCode == null){
            //����ֻ���δ��ȡ����
            msg.setStatus(-1);
            msg.setResult("�ֻ�����δ��ȡ����");
        }else if(sysCode.equals(userCode)){
            //�����ֻ�����Ͷ���һ�� , ��½�ɹ�
            Courier c = CourierService.findByCourierPhone(userPhone);
            Users user = new Users();
//            if("18888888888".equals(userPhone)){
//            System.out.println(c);
            if(c != null){
                //���Ա
                msg.setStatus(1);
                user.setUser(false);
            }else{
                //�û�
                msg.setStatus(0);
                user.setUser(true);
            }

            user.setUphone(userPhone);
            UserUtil.setWxUser(request.getSession(),user);
        }else{
            //��֤�벻һ�� , ��½ʧ��
            msg.setStatus(-2);
            msg.setResult("��֤�벻һ��,����");
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }

    @ResponseBody("/wx/userInfo.do")
    public String userInfo(HttpServletRequest request, HttpServletResponse response){
        Users user = UserUtil.getWxUser(request.getSession());
        boolean isUser = user.isUser();
        Message msg = new Message();
        if(isUser)
            msg.setStatus(0);
        else
            msg.setStatus(1);
        msg.setResult(user.getUphone());
        String json = JSONUtil.toJSON(msg);
        return json;
    }


    @ResponseBody("/wx/logout.do")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        //1.    ����session
        request.getSession().invalidate();
        //2.    ���ͻ��˻ظ���Ϣ
        Message msg = new Message(0);
        return JSONUtil.toJSON(msg);
    }
}
