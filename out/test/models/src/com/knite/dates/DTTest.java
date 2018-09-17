package com.carl.dates;

import com.carl.ibatis.domain.User;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by user on 2018/8/21.
 */
public class DTTest {
    public static void main(String[] args) {
//        Calendar lastT = Calendar.getInstance();
//        lastT.setTime(new Date());
//        lastT.add(Calendar.MINUTE, 5);
//        Date ps = lastT.getTime();
//
//        System.out.println(ps);
//        System.out.println(ps.before(new Date()));
//        System.out.println(lastT.after(new Date()));
//
//        //-----------
//        Date d1 = new Date();
        DTTest t = new DTTest();
        Object u= new Object();
        t.doSome(u);
        System.out.print(u instanceof User);
        System.out.print(1);
    }


    public void doSome(Object io) {
        if (io == null) {
            io = new User();
        }
        System.out.println(io);
    }
}
