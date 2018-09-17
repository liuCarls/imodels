package com.carl.pppd;

/**
 * Created by user on 2018/8/18.
 */
public class SThread extends BThread<OneObject> {




    @Override
    protected boolean isRight() {
        return false;
    }

    @Override
    public void execute() {
        OneObject obj = getObject();
        obj.m1();
    }
}
