package com.vinci.util;

import com.google.gson.Gson;

/**
 * @Author:Vinci_Ma
 * @Oescription: 将JSON对象变成json字符串
 * @Date Created in 2020-08-17-22:09
 * @Modified By:
 */
public class JSONUtil {
    private static Gson g = new Gson();
    public static String toJSON(Object obj){
        return g.toJson(obj);
    }
}
