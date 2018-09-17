package com.carl.redis.exm2;

import com.carl.serialize.SerializeUtil;
import redis.clients.jedis.Jedis;

/**
 * 自定义JSON转换工具类
 * Created by user on 2018/7/9.
 */
public class RedisOps {
    public static void set(String key,String value){
        Jedis jedis = RedisConnection.getJedis();
        jedis.set(key, value);
        jedis.close();
    }
    public static String get(String key){
        Jedis jedis = RedisConnection.getJedis();
        String value = jedis.get(key);
        jedis.close();
        return value;
    }
    public static void setObject(String key,Object object){
        Jedis jedis = RedisConnection.getJedis();
        jedis.set(key.getBytes(), SerializeUtil.serizlize(object));
        jedis.close();
    }
    public static Object getObject(String key){
        Jedis jedis = RedisConnection.getJedis();
        byte[] bytes = jedis.get(key.getBytes());
        jedis.close();
        return SerializeUtil.deserialize(bytes);
    }

}
