package com.carl.aop.exm02;

import com.carl.aop.exm01.IHuman;
import org.springframework.stereotype.Component;

/**
 * Created by user on 2018/9/15.
 */
@Component
public class Chinese implements IHuman {
    @Override
    public void chifan() {
        // TODO 自动生成的方法存根
        System.out.println("中国人吃饭");

    }

    @Override
    public void shuijiao() {
        // TODO 自动生成的方法存根
        System.out.println("中国人睡觉");
    }
}
