package com.me.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.me.security.TokenCreater;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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

    @Test
    public void cc(){
        String jsonStr = "[\n" +
                "      {\n" +
                "       \"value\": \"item1\",\n" +
                "       \"text\": \"撒旦发生短发\"\n" +
                "      },\n" +
                "      {\n" +
                "       \"value\": \"item2\",\n" +
                "       \"text\": \"啊多发点\"\n" +
                "      },\n" +
                "      \"item3\"\n" +
                "     ]";

        JSONArray jsonArray = JSONArray.parseArray(jsonStr);
        Iterator iterator = jsonArray.iterator();
        while(iterator.hasNext()){
            Object o = iterator.next();
            if(o instanceof JSONObject){
                System.out.println(o.toString());
            }else {
                System.out.println(o.toString());
            }
        }
    }

    @Test
    public void dd(){
        List mylist = new ArrayList();
        HashMap hashMap;
        int i=0;
        while(i<5){

        }
    }

}
