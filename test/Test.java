import com.carl.ibatis.domain.User;
//import org.apache.commons.codec.digest.DigestUtils;
import sun.misc.BASE64Encoder;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by user on 2018/6/23.
 */
public class Test {
    public static void main(String[] args){


        try {
//            EncoderByMd5("abcd12刘31231");
//            getMd5("abcd12刘31231");
//            int dd = 1530513000;
//            Date d = new Date(((long)dd)*1000);
//            DateFormat df = new SimpleDateFormat();
//            System.out.println(df.format(d));
            String uuid = UUID.randomUUID().toString();//转化为String对象

            System.out.println(uuid);//打印UUID
//            test2();
            String str = "{xxx}>50";
            System.out.println(str.toUpperCase());
//            uuidT();
            System.out.println(str.replaceAll("\\{.*\\}","20"));
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine se = manager.getEngineByName("js");
            if ((Boolean) se.eval(str.replaceAll("\\{.*\\}", "200"))) {
                System.out.println("success");
            }
            SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sDateFormat.parse("2018-11-28 08:18:24");
            System.out.println("2018-11-28 08:18:24".substring(0,4));

            System.out.println(date);
        } catch (Exception e) {
            e.printStackTrace();
        } 


    }

    public static void uuidT() {
//        System.out.println(UUID.fromString("abc-abc-def-aaa-aaaa"));
//        System.out.println(UUID.randomUUID());
//        System.out.print("GF12345".toLowerCase().startsWith("gf"));

        int id = 0x01010000;

        List<Long> ids = new ArrayList();
        ids.add((long)0x01010000);
        ids.add((long)0x01010001);
        ids.add((long)0x01010002);
        ids.add((long)0x02010100);
        ids.add((long)0x02010101);
        ids.add(0xf2010102L);
//        Long[] ids = [0x01010000,0x01010001,0x01010002,0x02010100,0x02010101,0x02010102];
        for (long d : ids) {
            System.out.println(d);
        }
    }


    public static void test4() {
        byte[] content = "abcd".getBytes();
        boolean isAppend = false;
        String fileName = "Test.txt";
        FileOutputStream fos = null;
        try {
            File file = new File("temp"+File.separator+fileName);
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            fos = new FileOutputStream(file, isAppend);
            fos.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void test3(){
        try {
            //方法一
            File f=new File("aa.txt");
            FileWriter fw = new FileWriter(f, true);
            fw.append('c'); //CharSequence
            PrintWriter pw = new PrintWriter(fw);
            pw.print(new String("abc".getBytes()));
//            new PrintStream();
//            char[] chars = {'c', 'd'};
            pw.close();
            //方法二
            for(int i=0;i<3;i++){
                FileOutputStream fos = new FileOutputStream("temp\\dd.txt", true);
                fos.write("abcd\r\n".getBytes());
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int mtStatic( int p) {
        int i = 1;
        if (Math.random()>0.5) {
            i++;
        }

        return i;
    }

    public static void test2() {
        final String str = "abc";
        final User u = new User();
        u.setName("abc");
        u.setAge(2);
        u.setId(1);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                int s=0;
                while (true){
                    try {
                        s = Test.mtStatic(s);
                        String tName = Thread.currentThread().getName();
                        System.out.println(tName+"\t"+s);
                        Thread.sleep(1000*5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    System.out.println(u.toString());
                }
            }
        });
        t.setName("Thread0");
        t.start();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int s=0;
                while (true){
                    try {
                        s = Test.mtStatic(s);
                        String tName = Thread.currentThread().getName();
                        System.out.println(tName+"\t"+s);
                        Thread.sleep(1000*6);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    System.out.println(u.toString());
                }
            }
        });
        t1.setName("Thread1");
        t1.start();
    }


    public static void test1(){

//        System.out.print(123456*0.1);
        byte[] byteArray = {1,112,-3,4,5,0,7,8,5,-10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,50};
        byte[] tmp = {103,119,49,51,48,51,48,51,51,50,49,48,0,-30,98,64,1,0,0,0};
//        byte[] tmp = {1,2,49,51,0,51,48,0};
//        byte[] tmp1 = "gw1303033210".getBytes();
        int location=0;
        for (byte b : tmp) {
            location++;
            if (b == 0) {
                location--;
                break;
            }

        }
        System.out.println(location);
        byte[] tmp1 = new byte[location-1];

        System.arraycopy(tmp,0,tmp1,0,location-1);
//        int location = Arrays.binarySearch(tmp, sV);
        System.out.println(new String(tmp1));
//        byte[] tt = Arrays.copyOf(ar, 3);
//        byte[] tt1 = Arrays.copyOfRange(ar,1,5);
//        System.out.println();
//        int[] fWidth = {4,2,8,2,byteArray.length-16};
//        int p=0;
//        int i=0;
//        while(i<fWidth.length){
//            byte[] tmp = Arrays.copyOfRange(byteArray, p, p + fWidth[i]);
//            p=p+fWidth[i];
//
//            System.out.println(Arrays.toString(tmp));
//            i++;
//        }
//        String[] aa = {"11","22","33"};
//        String[] bb = {"44","55","66"};
//        String[] cc = {"77","88","99"};

// 合并两个数组
//        String[] dd = new String[aa.length + bb.length];
//        System.arraycopy(aa, 0, dd, 0, aa.length);
//        System.arraycopy(bb, 0, dd, aa.length, bb.length);
//        System.out.println(Arrays.toString(aa));
//        System.out.println(Arrays.toString(dd));
//        int len = bb.length;
//        System.arraycopy(aa, 0, bb, len, aa.length);
//        System.out.println(Arrays.toString(bb));

//        int j = Integer.parseInt("");
////        int j = null;
//        System.out.print(j);

//        Integer ig;
//        ig = 1;
//        ig = null;
////        ig = new Integer(null);
//        System.out.print(ig);
//        String str = "gw2018061023";
//        byte[] arrs = str.getBytes();
//        System.out.println(Arrays.toString(arrs));
//        System.out.println(new String(arrs));
//        GatewayRtStatusProto.GatewayRtStatus grs;

//        byte b = 1;
//        byte b1 = 105;
//
//        int ii = 655350;
//        ii = 0x55;
//        ii = 200;
//        System.out.println(Integer.toBinaryString(ii));
//
////
//        System.out.println(ii+"\t"+(ii==85));
//        byte[] bs = intToByteArray(ii,false);
//        System.out.println(Arrays.toString(bs));
//        byte[] bs1 = new byte[1];
//        bs1[0]=85;
//        System.out.println(byteArrayToInt(bs));
    }

    public static int byteArrayToInt(byte[] bs) {
//        return   bs[3] & 0xFF |
//                (bs[2] & 0xFF) << 8 |
//                (bs[1] & 0xFF) << 16 |
//                (bs[0] & 0xFF) << 24;
            int len = bs.length;
            int o=0;
            for(int i=1;i<=len;i++){
                if(i==1){
                    o=(bs[len-i]&0xFF) << 8*(i-1);
                } else{
                    o = o|((bs[len-i]&0xFF) << 8*(i-1));
                }
            }
            return o;
    }
    public static byte[] intToByteArray(int a, boolean isFull) {
        if(isFull){
            return new byte[] {
                    (byte) ((a >> 24) & 0xFF),
                    (byte) ((a >> 16) & 0xFF),
                    (byte) ((a >> 8) & 0xFF),
                    (byte) (a & 0xFF)};
        } else {
            return new byte[]{
                    (byte) ((a >> 8) & 0xFF),
                    (byte) (a & 0xFF)};
        }
    }

    public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        //确定计算方法
        MessageDigest md5=MessageDigest.getInstance("MD5");
        byte[] md5P = md5.digest(str.getBytes());
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
//        return new String(md5P);
//        String newstr=base64en.encode(md5P);
        //  4vxxTEcn7pOV8yTNLn8zHw==
        //  4vxxTEcn7pOV8yTNLn8zHw==
        //
        System.out.println(newstr);
        return null;
    }

    public static String getMd5(String str) {
        try {
            MessageDigest md5=MessageDigest.getInstance("MD5");
            byte[] input = str.getBytes();
            byte[] buff = md5.digest(input);
            File file = new File("E:\\usr\\local\\tomcat8\\webapps\\Energy\\config.conf");
            FileInputStream fis = null;
            try {
                System.out.println(file.length());
                fis = new FileInputStream(file);
//                System.out.println(DigestUtils.md5Hex(fis));
            } catch (Exception e) {
                e.printStackTrace();
            }
            StringBuffer md5str = new StringBuffer();
            // 把数组每一字节换成16进制连成md5字符串
            int digital;
            for (int i = 0; i < buff.length; i++) {
                digital = buff[i];

                if (digital < 0) {
                    digital += 256;
                }
                if (digital < 16) {
                    md5str.append("0");
                }
                md5str.append(Integer.toHexString(digital));
            }
            System.out.println(md5str);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
