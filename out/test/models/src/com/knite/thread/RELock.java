package com.carl.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by user on 2018/7/6.
 */
public class RELock {

    public static void main(String[] args) {
        ReentrantLock rLock = new ReentrantLock();
        rLock.newCondition();
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    rLock.lock();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    rLock.unlock();
                }
            }
        });
    }
}
