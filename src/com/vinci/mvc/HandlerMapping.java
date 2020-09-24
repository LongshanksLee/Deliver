package com.vinci.mvc;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Author:Vinci_Ma
 * @Oescription: 映射器，包含了大量的网址与方法的对应关系
 * @Date Created in 2020-08-17-10:12
 * @Modified By:
 */
public class HandlerMapping {
    private static Map<String,MVCMapping> data = new HashMap<>();

    public static MVCMapping get(String uri){
        return data.get(uri);
    }

    public static void load(InputStream is){
        Properties ppt = new Properties();
        try {
            ppt.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取配置温江中描述的一个个的类
        Collection<Object> values = ppt.values();
        for (Object cla:values) {
            String className = (String) cla;
            try {
                //加载配置文件中描述的每一个类
                Class c = Class.forName(className);
                //创建这个类的对象（类描述的是处理请求的方法）
                Object obj = c.getConstructor().newInstance();
                //获取类中所有的方法
                Method[] methods = c.getMethods();
                for (Method m:methods) {
                    Annotation[] as = m.getAnnotations();
                    if (as != null){
                        for (Annotation annotation:as){
                            if (annotation instanceof ResponseBody){
                                //说明此方法，用于返回字符串给客户端
                                MVCMapping mapping = new MVCMapping(obj,m, ResponseType.TEXT);
                                Object o = data.put(((ResponseBody) annotation).value(),mapping);
                                if (o != null){
                                    //说明存在了重复的请求地址
                                    throw new RuntimeException("请求地址重复："+((ResponseBody) annotation).value());
                                }
                            }else if (annotation instanceof ResponseView){
                                //说明此方法，用于返回界面给客户端
                                MVCMapping mapping = new MVCMapping(obj,m,ResponseType.VIEW);
                                Object o = data.put(((ResponseView) annotation).value(),mapping);
                                if (o != null){
                                    //说明存在了重复的请求地址
                                    throw new RuntimeException("请求地址重复："+((ResponseView) annotation).value());
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Description 映射对象，每个对象封装一个方法，用于处理请求
     * @param null
     * @Return
     * @Author Vinci_Ma
     * @Date Created in 2020/8/17 10:14
     **/
    public static class MVCMapping{
        private Object obj;
        private Method method;
        private ResponseType type;

        public MVCMapping(Object obj, Method method, ResponseType type) {
            this.obj = obj;
            this.method = method;
            this.type = type;
        }

        public MVCMapping() {
        }

        public Object getObj() {
            return obj;
        }

        public void setObj(Object obj) {
            this.obj = obj;
        }

        public Method getMethod() {
            return method;
        }

        public void setMethod(Method method) {
            this.method = method;
        }

        public ResponseType getType() {
            return type;
        }

        public void setType(ResponseType type) {
            this.type = type;
        }
    }
}
