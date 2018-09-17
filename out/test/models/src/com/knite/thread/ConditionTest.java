package com.carl.thread;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by user on 2018/7/6.
 */
public class ConditionTest {

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(new Date() + "\tThread 1 is waiting");
                try {
                    long waitTime = condition.awaitNanos(TimeUnit.SECONDS.toNanos(2));
                    System.out.println(new Date() + "\tThread 1 remaining time " + waitTime);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println(new Date() + "\tThread 1 is waken up");
            } finally {
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            lock.lock();
            try{
                System.out.println(new Date() + "\tThread 2 is running");
                try {
                    Thread.sleep(4000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                condition.signal();
                System.out.println(new Date() + "\tThread 2 ended");
            } finally {
                lock.unlock();
            }
        }).start();
    }
}
/**

 条件对象提供以下方法以实现不同的等待语义
 await() 调用该方法的前提是，当前线程已经成功获得与该条件对象绑定的重入锁，
    否则调用该方法时会抛出IllegalMonitorStateException。调用该方法外，
    当前线程会释放当前已经获得的锁（这一点与上文讲述的Java内置锁的wait方法一致），
    并且等待其它线程调用该条件对象的signal()或者signalAll()方法
    （这一点与Java内置锁wait后等待notify()或notifyAll()很像）。
 或者在等待期间，当前线程被打断，则wait()方法会抛出InterruptedException并清除当前线程的打断状态。

 */