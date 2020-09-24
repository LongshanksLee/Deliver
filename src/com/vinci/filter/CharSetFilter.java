package com.vinci.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Author:Vinci_Ma
 * @Oescription: 处理中文乱码
 * @Date Created in 2020-08-18-17:13
 * @Modified By:
 */
@WebFilter("*.do")
public class CharSetFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        chain.doFilter(req, resp);
//        System.out.println(resp.getCharacterEncoding()+"  "+req.getCharacterEncoding());
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
