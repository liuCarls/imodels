package com.carl.littleS;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by user on 2018/8/23.
 */
public class StaticHandler implements Callable{
    private Function function;

    private StaticHandler(Function function) {
        this.function = function;
    }
    public static void execute(Function function) {
//        new Thread(new StaticHandler(function)).start();
        //这里换成Future,则可以返回参数，便于将响应与请求串起来。
        Callable<Result> callable = new StaticHandler(function);
        FutureTask<Result>  f = new FutureTask<Result>(callable);
        new Thread(f).start();
        Result result = null;
        try {
            result = f.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //根据情况，串接下一个功能单元
        System.out.println(result.getStatus());

    }
    //------------------
    public static boolean validChainContext(Function fun, Chain chain) {
        System.out.println("validChainContext"+fun.fType);
        return true;
    }

    @Override
    public Result call() throws Exception {
        //获得锁
        //获得链上下文
        Chain ctx = new Chain(function.sn, function.chain);

        validChainContext(function, ctx);

        function.isRight();

        //如果是响应function,则清除上下文中的超时重传项

        function.execute();

        //记录日志
        return new Result().setStatus(1);
    }
}
