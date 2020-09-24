package com.vinci.mvc;

import java.lang.annotation.*;

/**
 * @Author:Vinci_Ma
 * @Oescription: 注释的作用
 *       被此注释添加的方法，会被用于处理请求
 *      方法返回的内容，会直接重定向
 * @Date Created in 2020-08-17-10:09
 * @Modified By:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseView {
    String value();
}
