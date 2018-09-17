package com.carl.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by user on 2018/8/10.
 */
public class ObjectLockJudgement2 {

    private Lock lock = new ReentrantLock();
    /**
     * @param args
     */
    public static void main(String[] args) {
        final ObjectLockJudgement2 instance = new ObjectLockJudgement2();
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                boolean tryLock = instance.lock.tryLock();

                if (tryLock) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println(1);
                    instance.lock.unlock();
                } else {
                    System.out.println(2);
                }

            }
        });
//        t1.start();

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                boolean tryLock = instance.lock.tryLock();

                if (tryLock) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println(3);
                    instance.lock.unlock();
                } else {
                    System.out.println(4);
                }
            }
        });
        t2.start();
        t1.start();
//        t2.start();
    }
}
