package com.carl.aop.exm01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by user on 2018/9/15.
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("com/carl/aop/exm01/beans.xml");
        //要用接口接收
        IHuman c= (IHuman)context.getBean("chinese");
        c.chifan();
        c.shuijiao();
        System.out.println("*********************************");
        //要用接口接收
        IHuman a= (IHuman) context.getBean("american");
        a.chifan();
        a.shuijiao();

    }
}
