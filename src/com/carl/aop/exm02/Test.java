package com.carl.aop.exm02;

import com.carl.aop.exm01.IHuman;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by user on 2018/9/15.
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("com/carl/aop/exm02/beans.xml");
        Chinese c= context.getBean(Chinese.class);
        if (c instanceof Chinese) {

        }
        c.chifan();
        c.shuijiao();
        System.out.println("*********************************");
        American a= (American) context.getBean("american");
        a.chifan();
        a.shuijiao();

    }
}
