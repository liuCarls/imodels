package com.carl.littleS;

/**
 * Created by user on 2018/8/23.
 */
public class Function102 extends Function{

    public Function102(String sn) {
        super(sn, 102);
        this.chain = 1;
    }

    public int getChain() {
        return this.chain;
    }

    @Override
    public boolean isRight() {
        System.out.println(sn);
        return false;
    }

    @Override
    public void execute() {

    }
}
