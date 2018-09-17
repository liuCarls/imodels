package com.carl.littleS;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2018/8/23.
 */
public class Chain {
    public String sn;
    public int chain;
    //    public int hFun = 0; //最前面的功能节点,默认为0，在执行功能节点时重置
//    public BasicFunThread currFunT; //当前功能点
    public int nextFunId = -1; //下一个应该执行的功能点

    public int seek = 0; //交换文件的偏移量，默认为-1，
    public String md5;

    public boolean isFinish; //流程是否已经取消， true 已取消，false 未取消

    private Map<String, Object> properties = new HashMap<String, Object>();

    public Object getProperty(String key) {
        return properties.get(key);
    }

    public void setProperty(String key, Object value) {
        properties.put(key, value);
    }

    public Chain(String sn, int chain) {
        this.sn = sn;
        this.chain = chain;
    }


    public String getKey() {
        return sn + "_" + chain;
    }

//    public ChainLocker lock = new ChainLocker(sn, chain); //用于延迟主进程，等待链结束


    public Date lastTime = new Date();


//    public List<FunContext> funCtxs;

//    public FunContext getFunContext(int funid) {
////        for (FunContext fctx : funCtxs) {
////            if (fctx.funId == funid) {
////                return fctx;
////            }
////        }
//        return null;
//    }

    public void refreshLastTime() {
        lastTime = new Date();
    }
}
