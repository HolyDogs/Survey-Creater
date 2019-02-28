package com.me.demo;

import com.me.utils.RedisUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName RedisTest
 * @Description TODO
 * @Author xufeng
 * @Data 2019/2/28 16:45
 * @Version 1.0
 **/
public class RedisTest extends DemoApplicationTests {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void myTest(){
        redisUtil.hset("mymap","name","小强");
        System.out.println(redisUtil.hget("mymap","name"));
    }
}
