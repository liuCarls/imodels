package com.carl.timmer;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by user on 2018/8/27.
 */
public class TimerTest extends TimerTask {
    private String jobName = "";
    public TimerTest(String jobName) {
        super();
        this.jobName = jobName;
    }

    @Override
    public void run() {
        System.out.println("execute " + jobName);
    }

    public static void main(String[] args) {
//        Timer timer = new Timer();
        ScheduledExecutorService timer = Executors.newScheduledThreadPool(10);
        long delay1 = 1 * 1000;
        long period1 = 1000;
        // 从现在开始 1 秒钟之后，每隔 1 秒钟执行一次 job1
        TimerTest job1 = new TimerTest("job1");

//        timer.schedule(job1, delay1, period1);
        timer.schedule(job1, delay1, TimeUnit.MILLISECONDS);
        timer.scheduleWithFixedDelay(job1, delay1, delay1*2, TimeUnit.MILLISECONDS);
        long delay2 = 2 * 1000;
        long period2 = 2000;
        // 从现在开始 2 秒钟之后，每隔 2 秒钟执行一次 job2
//        timer.schedule(new TimerTest("job2"), delay2, period2);

        /**
         * schedule有以下四种形式：

         schedule(task,time);
         schedule(task,time,period);
         schedule(task,delay);
         schedule(task,delay,period);
         scheduleAtFixedRate(task,time,period);
         scheduleAtFixedRate(task,delay,period);
         其中，task是我们需要执行的任务，
            time是首次执行任务的时刻，
            period是执行一次task的时间间隔，
            delay是执行第一次任务前的延迟时间，单位均为毫秒。


         Timer里面的其它重要方法：
            cancel(),终止此计时器，丢弃所有当前已安排的任务，也可单独取消，如task1.cancel()；
            purge(),从此计时器的任务队列中移除所有已取消的任务，返回从队列中移除的任务数。



         TimeTask里面的其它重要方法：
            cancel(),取消当前TimeTask里的任务；
            scheduledExecutionTime(),返回此任务最近实际执行的已安排执行的时间。
         */

    }
}
