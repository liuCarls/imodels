package com.carl.timmer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * https://www.ibm.com/developerworks/cn/java/j-lo-taskschedule/
 * Created by user on 2018/8/20.
 */
public class ScheduledExecutorTest implements Runnable {
    private String jobName = "";

    public ScheduledExecutorTest(String jobName) {
        super();
        this.jobName = jobName;
    }

    @Override
    public void run() {
        System.out.println("execute " + jobName);
    }

    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

        long initialDelay1 = 1;
        long period1 = 1;
        // 从现在开始1秒钟之后，每隔1秒钟执行一次job1
        ScheduledExecutorTest job1 = new ScheduledExecutorTest("job1");

        service.scheduleAtFixedRate(job1
                , initialDelay1,
                period1, TimeUnit.SECONDS);

        long initialDelay2 = 1;
        long delay2 = 1;
        // 从现在开始2秒钟之后，每隔2秒钟执行一次job2
        service.scheduleWithFixedDelay(
                new ScheduledExecutorTest("job2"), initialDelay2,
                delay2, TimeUnit.SECONDS);
        service.shutdown();



    }
}
