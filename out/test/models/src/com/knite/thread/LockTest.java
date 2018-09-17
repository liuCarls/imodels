package com.carl.thread;

/**
 * Created by user on 2018/8/8.
 */
public class LockTest {
    Locker locker = new Locker("sn_"+1);
    public static void main(String[] args) {
        LockTest test = new LockTest();
        test.go();

        try {
            Thread.sleep(1000*6*1);
            synchronized (test.locker) {
                test.locker.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "**end**");

    }

    public void go() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+" begin...");
                try {
                    synchronized (locker) {
                        System.out.println(Thread.currentThread().getName() + "waiting");
                        locker.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" end ...");
            }
        };
        for(int i=0; i<5; i++) {
            new Thread(r).start();

        }
    }
}

