package com.carl.thread;

/**
 * Created by user on 2018/8/10.
 */
public class LockObj {

    public static void main(String[] args) {
        LockObj thiz = new LockObj();
        try {
            thiz.test1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        test1()
    }

    public void test1() throws InterruptedException {
        Object o = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(o) {
                    System.out.println("child thread: holdLock: " +
                            Thread.holdsLock(o)+"\t"+Thread.activeCount());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        System.out.println("main thread: holdLock: " + Thread.holdsLock(o));
        Thread.sleep(2000);

    }
}
