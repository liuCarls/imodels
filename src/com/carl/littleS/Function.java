package com.carl.littleS;

/**
 * Created by user on 2018/8/23.
 */
public abstract class Function {
    String sn;
    int gwType;
    int fType;
    int chain;

    public Function(int gwType, int fType) {
        this.gwType = gwType;
        this.fType = fType;
    }

    public Function(String sn, int fType) {
        this.sn = sn;
        this.fType = fType;
    }
    public abstract boolean isRight();

    public abstract void execute();
}
