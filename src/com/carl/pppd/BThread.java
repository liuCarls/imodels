package com.carl.pppd;

import com.carl.pppd.subP.SubCTester;

/**
 * Created by user on 2018/8/18.
 */
public class BThread<T> extends Thread {
    public T t;
    @Override
    public void run() {
        if (isRight()) {
            System.out.println("Super");
        } else {
            System.out.println("Suber");
        }
        execute();

    }

    public void execute() {

    }
    protected boolean isRight() {
        return true;
    }

    public T getObject() {
        return (T) new SubCTester();

    }


}
