package com.vinci.wx.controller;

import com.vinci.bean.BootStrapTableExpress;
import com.vinci.bean.Express;
import com.vinci.bean.Message;
import com.vinci.bean.Users;
import com.vinci.mvc.ResponseBody;
import com.vinci.service.ExpressService;
import com.vinci.util.DateFormatUtil;
import com.vinci.util.JSONUtil;
import com.vinci.util.UserUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ExpressController {

    @ResponseBody("/wx/findExpressByUserPhone.do")
    public String findByUserPhone(HttpServletRequest request, HttpServletResponse response){
        Users wxUser = UserUtil.getWxUser(request.getSession());
        String userPhone = wxUser.getUphone();
        List<Express> list = ExpressService.findByUserPhone(userPhone);
        List<BootStrapTableExpress> list2 = new ArrayList<>();
        for(Express e:list){
            String inTime = DateFormatUtil.format(e.getInTime());
            String outTime = e.getOutTime()==null?"未出库":DateFormatUtil.format(e.getOutTime());
            String status = e.getStatus()==0?"待取件":"已取件";
            String code = e.getCode()==null?"已取件":e.getCode();
            BootStrapTableExpress e2 = new BootStrapTableExpress(e.getId(),e.getNumber(),e.getUsername(),e.getUserPhone(),e.getCompany(),code,inTime,outTime,status,e.getSysPhone());
            list2.add(e2);
        }
        Message msg = new Message();
        if(list.size()==0){
            msg.setStatus(-1);
        }else{
            msg.setStatus(0);
            Stream<BootStrapTableExpress> status0Express = list2.stream().filter(express -> {
                if (express.getStatus().equals("待取件")) {
                    return true;
                } else {
                    return false;
                }
            }).sorted((o1,o2) -> {
                long o1Time = DateFormatUtil.toTime(o1.getInTime());
                long o2Time = DateFormatUtil.toTime(o2.getInTime());
                return (int)(o1Time-o2Time);
            });
            Stream<BootStrapTableExpress> status1Express = list2.stream().filter(express -> {
                if (express.getStatus().equals("已取件")) {
                    return true;
                } else {
                    return false;
                }
            }).sorted((o1,o2) -> {
                long o1Time = DateFormatUtil.toTime(o1.getInTime());
                long o2Time = DateFormatUtil.toTime(o2.getInTime());
                return (int)(o1Time-o2Time);
            });
            Object[] s0 = status0Express.toArray();
            Object[] s1 = status1Express.toArray();
            Map data = new HashMap<>();
            data.put("status0",s0);
            data.put("status1",s1);
            msg.setData(data);
        }
        String json = JSONUtil.toJSON(msg.getData());
        return json;
    }

    @ResponseBody("/wx/userExpressList.do")
    public String expressList(HttpServletRequest request,HttpServletResponse response){
        String userPhone = request.getParameter("userPhone");
        List<Express> list = ExpressService.findByUserPhoneAndStatus(userPhone, 0);
        List<BootStrapTableExpress> list2 = new ArrayList<>();
        for(Express e:list){
            String inTime = DateFormatUtil.format(e.getInTime());
            String outTime = e.getOutTime()==null?"未出库":DateFormatUtil.format(e.getOutTime());
            String status = e.getStatus()==0?"待取件":"已取件";
            String code = e.getCode()==null?"已取件":e.getCode();
            BootStrapTableExpress e2 = new BootStrapTableExpress(e.getId(),e.getNumber(),e.getUsername(),e.getUserPhone(),e.getCompany(),code,inTime,outTime,status,e.getSysPhone());
            list2.add(e2);
        }
        Message msg = new Message();
        if(list.size() == 0){
            msg.setStatus(-1);
            msg.setResult("未查询到快递");
        }else{
            msg.setStatus(0);
            msg.setResult("查询成功");
            msg.setData(list2);
        }
        return JSONUtil.toJSON(msg);
    }

    @ResponseBody("/wx/findExpressByNum.do")
    public String findExpressByNumjump(HttpServletRequest request,HttpServletResponse response){
        String number = request.getParameter("expressNum");
        Express e = ExpressService.findByNumber(number);
        BootStrapTableExpress e2 =null;
        if(e!=null){
            e2 = new BootStrapTableExpress(e.getId(), e.getNumber(), e.getUsername()
                    , e.getUserPhone(), e.getCompany(), e.getCode(),
                    DateFormatUtil.format(e.getInTime()), e.getOutTime()==null?"未出库":DateFormatUtil.format(e.getOutTime()),e.getStatus()==0?"待取件":"已取件", e.getSysPhone());
        }
        Message msg = new Message();
        if(e == null){
            msg.setStatus(-1);
            msg.setResult("快递不存在");
        }else{
            msg.setStatus(0);
            msg.setResult("查询成功");
            msg.setData(e2);
        }
        String json = JSONUtil.toJSON(msg);
//        System.out.println("jump"+json);
        return json;
    }
}
