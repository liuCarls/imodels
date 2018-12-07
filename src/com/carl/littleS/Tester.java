package com.carl.littleS;

import java.lang.reflect.Method;

/**
 * Created by user on 2018/8/23.
 */
public class Tester {
    public static void main(String[] args) {
//        Function102 fun102 = new Function102("sn102");
//        Function3 fun3 = new Function3("sn3");
//        StaticHandler.execute(fun102);
//        StaticHandler.execute(fun3);

        // reflect .......
        Tester thiz = new Tester();
        Result res1 = thiz.getCObject(new Result());
//        System.out.println(res1.status);
        Result res2 = thiz.getCObject2(Result.class);
        System.out.println(res2.status);
    }


    public <T> T getCObject(T t) {
        Class<?> clazz = t.getClass();
        System.out.println(clazz);
//        Method[] methods = clazz.getMethods(); //包括继承的方法
        Method[] methods = clazz.getDeclaredMethods(); //本类声明的方法
//        Method method = clazz.getEnclosingMethod(); //
//        System.out.println(method.getName());
        for (Method md : methods) {
            System.out.println(md.getName());

        }
        //怎么处理这个t呢
        return null;
    }

    public <T extends Result> T getCObject2(Class T) {
        Class[] obj = T.getInterfaces();
        try {
            Object obj2 = T.newInstance();
            return (T) obj2;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //怎么处理这个t呢
        return null;
    }
}
