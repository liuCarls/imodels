package com.carl.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by user on 2018/8/10.
 */
public class ObjectLockJudgement {
    private static final ConcurrentHashMap<Object, Thread> relation = new ConcurrentHashMap<Object, Thread>();

    /**
     * @param args
     */
    public static void main(String[] args) {
        final int threadNumber = 100;

        List<Object> lockers = new ArrayList<Object>(threadNumber);
        for (int i = 0; i < threadNumber; i++) {
            final Object locker = new Object();
            lockers.add(locker);
            new ThreadImpl(locker);
        }

        while (!relation.isEmpty()) {
            for (final Object locker : lockers) {
                final Thread t = relation.get(locker);
                if (t != null)
                    System.out.println("the locker Object:" + locker.hashCode()
                            + " is held by thread:" + t.getName());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ThreadImpl extends Thread {
        private Object locker;
        private static final Random r = new Random();

        public ThreadImpl(Object locker) {
            this.locker = locker;
            this.start();
        }

        @Override
        public void run() {
            try {
                synchronized (locker) {
                    relation.put(locker, Thread.currentThread());
                    try {
                        Thread.sleep(r.nextInt(10000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                relation.remove(locker);
            }
        }
    }
    /**
     * 以上代码是将locker和thread做关联，可以得到object 锁对应的thread。
     但是我不知道判断的目的是什么，因为ReentrantLock的try locker,
     可以应该大的你相同的目的，而且不需要判断。
     */
}
