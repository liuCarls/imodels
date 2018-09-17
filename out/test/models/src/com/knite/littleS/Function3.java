package com.carl.littleS;

/**
 * Created by user on 2018/8/23.
 */
public class Function3 extends Function{
    public Function3(String sn) {
        super(sn, 3);
        this.chain = 1;
    }

    public int getChain() {
        return this.chain;
    }
    @Override
    public boolean isRight() {
        System.out.println("the function id is " + fType);
        return false;
    }

    @Override
    public void execute() {


    }
}
