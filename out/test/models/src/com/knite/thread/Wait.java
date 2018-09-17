package com.carl.thread;

import java.util.Date;

/**
 * Created by user on 2018/7/6.
 */
public class Wait {
    public static void main(String[] args) {
        try {
            Wait.class.wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread thread1 = new Thread(() -> {
            synchronized (Wait.class) {
                try {
                    System.out.println(new Date() + " Thread1 is running");
                    Wait.class.wait();
                    System.out.println(new Date() + " Thread1 ended");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            synchronized (Wait.class) {
                try {
                    System.out.println(new Date() + " Thread2 is running");
                    Wait.class.notify();
                    // Don't use sleep method to avoid confusing
                    for(long i = 0; i < 200000; i++) {
                        for(long j = 0; j < 100000; j++) {}
                    }
                    System.out.println(new Date() + " Thread2 release lock");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            for(long i = 0; i < 200000; i++) {
                for(long j = 0; j < 100000; j++) {}
            }
            System.out.println(new Date() + " Thread2 ended");
        });

        // Don't use sleep method to avoid confusing
        for(long i = 0; i < 200000; i++) {
            for(long j = 0; j < 100000; j++) {}
        }
        thread2.start();
    }
}

/**
 * 从运行结果可以看出

 thread1执行wait后，暂停执行
 thread2执行notify后，thread1并没有继续执行，因为此时thread2尚未释放锁，thread1因为得不到锁而不能继续执行
 thread2执行完synchronized语句块后释放锁，thread1得到通知并获得锁，进而继续执行

 注意：wait方法需要释放锁，前提条件是它已经持有锁。所以wait和notify（或者notifyAll）方法都必须被包裹在synchronized语句块中，并且synchronized后锁的对象应该与调用wait方法的对象一样。否则抛出IllegalMonitorStateException
 *
 */