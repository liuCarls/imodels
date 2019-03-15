package com.carl.ienum.m2;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.IOException;

/**
 * Created by user on 2018/8/9.
 */
public class ITest {
    public static void main(String[] args) {
        Color blue = Color.Blue;
        System.out.println(blue.getSort());
        blue.execute();
    }

    public void json() {
        JsonFactory jf = null;
        JsonParser  jp;
        try {
            jp = jf.createParser("");
            JsonToken t = jp.getCurrentToken();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
