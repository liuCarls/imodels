package com.carl.aop.exm02;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by user on 2018/9/15.
 */
@Aspect //声明该类是切面类
@Component//配置文件中启动自动扫描功能
public class Qiemian {

    @Before("execution(* com.carl.aop.exm02.*.chifan(..))")//在那个方法的之前通知    *代表全部..代表所有形参，不管有多少
    public void chifanqian(){
        System.out.println("洗手");
    }
    @After("execution(* com.carl.aop.exm02.*.chifan(..))")//在那个方法之后通知
    public void chifanhou(){
        System.out.println("漱口");
    }
    @Before("execution(* com.carl.aop.exm02.*.shuijiao(..))")
    public void shuijiaoqian(){
        System.out.println("洗澡");

    }

}
