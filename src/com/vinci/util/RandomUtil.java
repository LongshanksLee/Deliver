package com.vinci.util;

import java.util.Random;

/**
 * @Author:Vinci_Ma
 * @Oescription: 生成随机数，获得取件码
 * @Date Created in 2020-08-21-17:32
 * @Modified By:
 */
public class RandomUtil {
    public static Random r = new Random();
    public static int getcode(){
        return r.nextInt(900000) + 100000;
    }
}
