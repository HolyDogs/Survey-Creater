package com.me.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.me.security.TokenCreater;
import org.junit.Test;

/**
 * @ClassName ArrayToStringTest
 * @Description TODO
 * @Author xufeng
 * @Data 2019/2/28 17:43
 * @Version 1.0
 **/
public class ArrayToStringTest {

    @Test
    public void aa(){
        String object = "{\"first\":\"哈哈哈\",\"second\":[\"item1\",\"item2\",\"item3\"],\"third\":{\"item1\":\"aaa\",\"item2\":\"bbbb\"},\"forth\":[{\"t1\":\"t2\"},{\"t2\":4,\"t7\":true}],\"fivth\":true}";
        JSONObject object1 = JSONObject.parseObject(object);
        System.out.println(object1.getString("first"));
        System.out.println(object1.getString("second"));
        System.out.println(object1.getString("third"));
        System.out.println(object1.getString("forth"));
        System.out.println(object1.getString("fivth"));
    }

    @Test
    public void bb(){
        String token = TokenCreater.createJWT("dfd@qq.com","asdfasdf","asdfasdf",120000);
        System.out.println(token);

        TokenCreater.parseJWT(token);
    }
}