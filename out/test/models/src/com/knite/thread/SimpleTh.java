package com.carl.thread;

/**
 * Created by user on 2018/8/18.
 */
public class SimpleTh {
    public static void main(String[] args) {
        IThread t = new IThread();
        t.start();
        t.start();
    }
}


class IThread extends Thread {

    public IThread() {
        this.init();
    }

    private void init() {
        System.out.println("init");
    }

    @Override
    public void run() {
        System.out.println("-------");
    }
}