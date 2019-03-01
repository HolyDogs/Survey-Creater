package com.me.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @ClassName JSONResultFormatterUtils
 * @Description TODO
 * @Author xufeng
 * @Data 2019/2/28 11:18
 * @Version 1.0
 **/
public class JSONResultFormatterUtils {

    static String TRUE = "true";
    static String BOOLEAN = "boolean";

    public static HashMap forResult(String result,RedisUtil redisUtil,String pageId){
        HashMap rMap = new HashMap(64);
        JSONObject resultJson = JSONObject.parseObject(result);
        Iterator iterator = resultJson.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            if (redisUtil.hget(pageId, (String) entry.getKey()).equals(BOOLEAN)){
                rMap.put(entry.getKey(),entry.getValue().toString().equals(TRUE)?1:0);
                continue;
            }
            rMap.put(entry.getKey(),entry.getValue().toString());
        }
        return rMap;
    }
}
