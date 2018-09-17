package com.carl.littleS;

/**
 * Created by user on 2018/8/23.
 */
public class Tester {
    public static void main(String[] args) {
        Function102 fun102 = new Function102("sn102");
        Function3 fun3 = new Function3("sn3");
        StaticHandler.execute(fun102);
        StaticHandler.execute(fun3);

    }
}
