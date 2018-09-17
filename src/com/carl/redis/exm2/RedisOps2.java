package com.carl.redis.exm2;

import redis.clients.jedis.Jedis;

/**使用fastjson将对象转为json字符串后存储
 * Created by user on 2018/7/9.
 */
public class RedisOps2 {
    public static void setJsonString(String key,Object object){
        Jedis jedis = RedisConnection.getJedis();
//        jedis.set(key, JSON.toJSONString(object));
        jedis.close();
    }
    public static Object getJsonObject(String key,Class clazz){
        Jedis jedis = RedisConnection.getJedis();
        String value = jedis.get(key);
        jedis.close();
//        return JSON.parseObject(value, clazz);
         return "";
    }

}
