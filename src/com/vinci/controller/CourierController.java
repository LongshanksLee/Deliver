package com.vinci.controller;

import com.vinci.bean.*;
import com.vinci.mvc.ResponseBody;
import com.vinci.service.AdminService;
import com.vinci.service.CourierService;
import com.vinci.util.DateFormatUtil;
import com.vinci.util.JSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author:Vinci_Ma
 * @Oescription:
 * @Date Created in 2020-08-24-2:12
 * @Modified By:
 */
public class CourierController{

    @ResponseBody("/courier/console.do")
    public String console(HttpServletRequest request, HttpServletResponse response){
        List<Map<String, Integer>> data = CourierService.console();
        Message msg = new Message();
        if (data.size() == 0){
            msg.setStatus(-1);
        }else {
            msg.setStatus(0);
        }
        msg.setData(data);
        String json = JSONUtil.toJSON(msg);
        return json;
    }

    @ResponseBody("/courier/list.do")
    public String findAll(HttpServletRequest request, HttpServletResponse response) {
        //1.    ��ȡ��ѯ���ݵ���ʼ����ֵ
        int offset = Integer.parseInt(request.getParameter("offset"));
        //2.    ��ȡ��ǰҳҪ��ѯ��������
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        //3.    ���в�ѯ
        List<Courier> list = CourierService.findAll(true, offset, pageNumber);
        List<BootStrapTableCourier> list2 = new ArrayList<>();
        for (Courier c:list){
//            System.out.println(c.getLogintime());
            String signuptime = DateFormatUtil.format(c.getLogintime());
            String logintime = DateFormatUtil.format(c.getLogintime());
            BootStrapTableCourier c2 = new BootStrapTableCourier(c.getId(),c.getCouriername(),c.getCourierphone(),c.getIdcard(),c.getCode(),signuptime,logintime);
            System.out.println(c2);
            list2.add(c2);
        }
        List<Map<String, Integer>> console =CourierService.console();
        Integer total = console.get(0).get("data_size");
        //4�������Ϸ�װΪ bootstrap-tableʶ��ĸ�ʽ
        ResultData<BootStrapTableCourier> data = new ResultData<>();
        data.setRows(list2);
        data.setTotal(total);
        //ת����ΪJSON��ʽ
        String json = JSONUtil.toJSON(data);
        return json;
    }

    @ResponseBody("/courier/find.do")
    public String findByCourierPhone(HttpServletRequest request, HttpServletResponse response) {
        String courierphone = request.getParameter("phoneNum");
        Courier c = CourierService.findByCourierPhone(courierphone);
        Message msg = new Message();
        if (c == null){
            msg.setStatus(-1);
            msg.setResult("�û�������");
        }else {
            msg.setStatus(0);
            msg.setResult("��ѯ�ɹ�");
            msg.setData(c);
        }
        String json = JSONUtil.toJSON(msg);
//        System.out.println(json);
        return json;
    }

    @ResponseBody("/courier/insert.do")
    public String inset(HttpServletRequest request, HttpServletResponse response) {
        String couriername = request.getParameter("couriername");
        String courierphone = request.getParameter("courierphone");
        String idcard = request.getParameter("idCard");
        String code = request.getParameter("code");
        Courier c = new Courier(couriername,courierphone,idcard,code);
        boolean flag = CourierService.inset(c);
        Message msg = new Message();
        if (flag){
            //¼��ɹ�
            msg.setStatus(0);
            msg.setResult("���Ա��Ϣ¼��ɹ�!");
        }else {
            //¼��ʧ��
            msg.setStatus(-1);
            msg.setResult("���Ա��Ϣ¼��ʧ��!");
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }

    @ResponseBody("/courier/update.do")
    public String update(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String couriername = request.getParameter("couriername");
        String courierphone = request.getParameter("courierphone");
        String idcard = request.getParameter("idCard");
        String code = request.getParameter("code");
        Courier newCourier = new Courier();
        newCourier.setId(id);
        newCourier.setCouriername(couriername);
        newCourier.setCourierphone(courierphone);
        newCourier.setIdcard(idcard);
        newCourier.setCode(code);
        boolean flag = CourierService.update(courierphone,newCourier);
        Message msg = new Message();
        if (flag){
            msg.setStatus(0);
            msg.setResult("���Ա��Ϣ�޸ĳɹ�");
            //��½ʱ��ĸ���
            Date date = new Date();
            CourierService.updateLoginTime(courierphone,date);
        }else {
            msg.setStatus(-1);
            msg.setResult("���Ա��Ϣ�޸�ʧ��");
        }
        String json = JSONUtil.toJSON(msg);
        System.out.println(json);
        return json;
    }

    @ResponseBody("/courier/delete.do")
    public String delete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("courierid"));
        boolean flag = CourierService.delete(id);
        Message msg = new Message();
        if(flag){
            msg.setStatus(0);
            msg.setResult("���Ա��Ϣɾ���ɹ�");
        }else{
            msg.setStatus(-1);
            msg.setResult("���Ա��Ϣɾ��ʧ��");
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }
}
