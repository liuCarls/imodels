package com.carl.redis.exm2;

import com.carl.ibatis.domain.User;

/**
 * Created by user on 2018/7/9.
 */
public class RedisTest {
//    @Test
    public void testString(){
        RedisOps.set("user:1", "sisu");
        String user = RedisOps.get("user:1");
//        Assert.assertEquals("sisu", user);
    }

//    @Test
    public void testObject(){
//        RedisOps.setObject("user:2",new User(2,"lumia"));
        User user = (User)RedisOps.getObject("user:2");
//        Assert.assertEquals("lumia", user.getName());
    }
}
