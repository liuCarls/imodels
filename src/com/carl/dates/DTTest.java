package com.carl.dates;

import com.carl.ibatis.domain.User;

import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by user on 2018/8/21.
 */
public class DTTest {
    public static void main(String[] args) {
//        Calendar lastT = Calendar.getInstance();
//        lastT.setTime(new Date("2018/10/12"));
//        lastT.add(Calendar.MINUTE, 5);
//        Date ps = lastT.getTime();
//
//        System.out.println("ps is "+ps);
//
//        System.out.println(ps.before(new Date()));
//        System.out.println(ps.after(new Date()));
//        System.out.println(lastT.after(new Date()));
        DTTest thiz = new DTTest();
        thiz.duration();
//
//        //-----------
//        Date d1 = new Date();
//        DTTest t = new DTTest();
//        Object u= new Object();
//        t.doSome(u);
//        System.out.print(u instanceof User);
//        System.out.print(1);
    }


    public void doSome(Object io) {
        if (io == null) {
            io = new User();
        }
        System.out.println(io);
    }

    public void duration() {
//https://blog.csdn.net/sy793314598/article/details/79544796
        //传统的SimpleDateFormat类

        //java 7中的日历类Calendar

        //java 8中的周期类Period

        //java 8中的Duration类
        Instant ins1 = Instant.now();

        try {
            Thread.sleep(1000*5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Instant ins2 = Instant.now();
        System.out.println("以毫秒计的时间差：" + Duration.between(ins1, ins2).toMillis());

        //java 8中的ChronoUnit类
    }
}
