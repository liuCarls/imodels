package com.carl.thread;

/**
 * Created by user on 2018/8/7.
 */
public class MultiThread {
    public static void main(String[] args) {

        for(int i=5; i<50; ){
            final ShareDate sd = new ShareDate();
            sd.s = i;
            i = i+40;
            new Thread(){
                @Override
                public void run() {
                    MultiThread.test(sd.s);

                }
            }.start();
        }


    }

    public static void test(int p) {
        int i=0;
        while (i++ < 10){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(p>50){
                try {
                    Thread.sleep(1000*10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            p++;
            System.out.println(Thread.currentThread().getName()+":\t"+p);
        }
    }
}

class ShareDate {
    int s;
}

