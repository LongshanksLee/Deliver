package com.vinci.filter;

import com.vinci.util.UserUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:Vinci_Ma
 * @Oescription: Ȩ�޿��ƹ�����
 * @Date Created in 2020-08-22-10:11
 * @Modified By:
 */

@WebFilter({"/admin/index.html","/admin/views/*","/express/*"})
//@WebFilter({"/admin/index.html"})
public class AccessControlFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String userName = UserUtil.getUserName(request.getSession());
        if(userName != null)
            chain.doFilter(req, resp);
        else
            response.sendError(404,"���ź�,Ȩ�޲���");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
