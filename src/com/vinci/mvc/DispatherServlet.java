package com.vinci.mvc;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author:Vinci_Ma
 * @Oescription:
 * @Date Created in 2020-08-17-12:18
 * @Modified By:
 */
public class DispatherServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        //获得配置文件
        String path = config.getInitParameter("contentConfigLocation");
        //获得输入流，读取配置文件
        InputStream is = DispatherServlet.class.getClassLoader().getResourceAsStream(path);
        HandlerMapping.load(is);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取用户请求的uri
        String uri = req.getRequestURI();
        HandlerMapping.MVCMapping mapping = HandlerMapping.get(uri);
        if (mapping == null){
            resp.sendError(404,"自定义MVC，映射地址不存在"+uri);
        }
        Object obj = mapping.getObj();
        Method method = mapping.getMethod();
        Object result = null;
        try {
            result = method.invoke(obj, req, resp);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        switch (mapping.getType()){
            case TEXT:
                resp.getWriter().write((String) result);
                break;
            case VIEW:
                resp.sendRedirect((String) result);
                break;
        }
    }


}
