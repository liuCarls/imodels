package com.carl.thread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by user on 2018/7/18.
 */
public class MultiThreadReqResp {

    public static void main(String[] args) {
        for(int i=1;i<5; i++){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            uppRequest(i);
        }
    }


    public static void uppRequest( int num) {

        new Thread("Request"+num+"_") {
            @Override
            public void run() {
                //后台请求
                Locker lock = new Locker(Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName()+ "Outer request...");
                LockManager.addLock(lock);
                Thread t = new Thread(Thread.currentThread().getName()) {
                    @Override
                    public void run() {
//                        this.setName(Thread.currentThread().getName()+"response_thread");
                        //后台响应
                        try {
                            Thread.sleep(1000 * 6);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println(Thread.currentThread().getName()+"Inner request...");
                        Locker lock1 = LockManager.getLock(Thread.currentThread().getName());
                        synchronized (lock1) {
                            if("Request1_".equals(Thread.currentThread().getName())){
                                lock1.notify();
                            }
                        }
                    }
                };
                t.start();
                synchronized(lock) {
                    try {
                        lock.wait(1000*60*1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(Thread.currentThread().getName()+ "Outer request end");
//                System.out.println(t.getName());
            }
        }.start();

    }


}

class Locker {
    private String name;

    public Locker(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class LockManager{
    static Map<String, Locker> map = new ConcurrentHashMap<>();
    public static Locker getLock(String lockName){
        return map.get(lockName);
    }

    public static void addLock(Locker locker) {
        map.put(locker.getName(), locker);
    }

    public static void deleteLock(String lockName) {
        map.remove(lockName);
    }
}