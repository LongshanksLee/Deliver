package com.vinci.controller;

import com.vinci.bean.*;
import com.vinci.mvc.ResponseBody;
import com.vinci.service.UsersService;
import com.vinci.util.DateFormatUtil;
import com.vinci.util.JSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author:Vinci_Ma
 * @Oescription:
 * @Date Created in 2020-08-26-20:56
 * @Modified By:
 */
public class UsersController{

    @ResponseBody("/users/console.do")
    public String console(HttpServletRequest request, HttpServletResponse response){
        List<Map<String, Integer>> data = UsersService.console();
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

    @ResponseBody("/users/list.do")
    public String findAll(HttpServletRequest request, HttpServletResponse response) {
        //1.    获取查询数据的起始索引值
        int offset = Integer.parseInt(request.getParameter("offset"));
        //2.    获取当前页要查询的数据量
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        //3.    进行查询
        List<Users> list = UsersService.findAll(true, offset, pageNumber);
        System.out.println("list:"+list);
        List<BootStrapTableUsers> list2 = new ArrayList<>();
        for (Users users:list){
            System.out.println(users.getLogintime());
            String signuptime = DateFormatUtil.format(users.getLogintime());
            String logintime = DateFormatUtil.format(users.getLogintime());
            BootStrapTableUsers users2 = new BootStrapTableUsers(users.getId(),users.getUname(),users.getUphone(),users.getUidcard(),users.getUcode(),signuptime,logintime,users.getStatus());
            list2.add(users2);
//            System.out.println(users2);
        }
        List<Map<String, Integer>> console = UsersService.console();
        Integer total = console.get(0).get("data_size");
        //4、将集合封装为 bootstrap-table识别的格式
        ResultData<BootStrapTableUsers> data = new ResultData<>();
        data.setRows(list2);
        data.setTotal(total);
        //转换成为JSON格式
        String json = JSONUtil.toJSON(data);
        return json;
    }

    @ResponseBody("/users/find.do")
    public String findByUsersPhone(HttpServletRequest request, HttpServletResponse response) {
        String uphone = request.getParameter("phoneNum");
        Users users = UsersService.findByUsersPhone(uphone);
        Message msg = new Message();
        if (users == null){
            msg.setStatus(-1);
            msg.setResult("用户不存在");
        }else {
            msg.setStatus(0);
            msg.setResult("查询成功");
            msg.setData(users);
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }

    @ResponseBody("/users/insert.do")
    public String insert(HttpServletRequest request, HttpServletResponse response) {
        String unanme = request.getParameter("uname");
        String uphone = request.getParameter("uphone");
        String uidcard = request.getParameter("uidCard");
        String ucode = request.getParameter("ucode");
        Users users = new Users(unanme, uphone, uidcard, ucode);
        boolean flag = UsersService.inset(users);
        Message msg = new Message();
        if (flag) {
            //录入成功
            msg.setStatus(0);
            msg.setResult("用户信息录入成功!");
        } else {
            //录入失败
            msg.setStatus(-1);
            msg.setResult("用户信息录入失败!");
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }

    @ResponseBody("/users/update.do")
    public String update(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String unanme = request.getParameter("uname");
        String uphone = request.getParameter("uphone");
        String uidcard = request.getParameter("uidCard");
        String ucode = request.getParameter("ucode");
        Users newUsers = new Users(unanme, uphone, uidcard, ucode);
        newUsers.setId(id);
        newUsers.setUname(unanme);
        newUsers.setUphone(uphone);
        newUsers.setUidcard(uidcard);
        newUsers.setUcode(ucode);
        boolean flag = UsersService.update(uphone,newUsers);
        System.out.println(newUsers);
        Message msg = new Message();
        if (flag){
            msg.setStatus(0);
            msg.setResult("用户信息修改成功");

        }else {
            msg.setStatus(-1);
            msg.setResult("用户信息修改失败");
        }
        String json = JSONUtil.toJSON(msg);
        System.out.println(json);
        return json;
    }

    @ResponseBody("/users/delete.do")
    public String delete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("uid"));
        boolean flag = UsersService.delete(id);
        Message msg = new Message();
        if(flag){
            msg.setStatus(0);
            msg.setResult("用户信息删除成功");
        }else{
            msg.setStatus(-1);
            msg.setResult("用户信息删除失败");
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }
}
