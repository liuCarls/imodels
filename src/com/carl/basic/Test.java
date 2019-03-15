package com.carl.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by user on 2019/1/22.
 */
public class Test {
    public static void main(String[] args){
//        String result = Integer.toBinaryString(60);
//        ;
//        System.out.println(result);
//        System.out.println(Integer.toHexString(356));
//
////        System.out.println(Integer.parseInt("19"));
//
//        System.out.println(toHex(356, 2));
//        System.out.println(Integer.parseUnsignedInt("ffffffff", 16));
//        System.out.println(Long.parseLong("oxffffffff", 16));
        List<String> lis = new ArrayList<>();
        while (true) {
            String guid = UUID.randomUUID().toString();
            if(!lis.contains(guid)){
                lis.add(guid);
                System.out.println(lis.size());
            } else {
                System.out.println(guid);
                break;
            }
        }

    }

    private static String toHex(int num, int len){
        String res = Integer.toHexString(num);
        int n = len - res.length();
        StringBuffer sb = new StringBuffer(res);
        for(int i=0;i<n;i++){
            sb.insert(0,'0');
        }
        System.out.println(sb.charAt(0));
        System.out.println(sb.deleteCharAt(0));
        return sb.toString();
    }


}
