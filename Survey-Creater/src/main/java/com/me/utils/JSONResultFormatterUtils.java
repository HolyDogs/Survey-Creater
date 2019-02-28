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
    public static HashMap forResult(String result){
        HashMap rMap = new HashMap(64);
        JSONObject resultJson = JSONObject.parseObject(result);
        Iterator iterator = resultJson.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            rMap.put(entry.getKey(),entry.getValue().toString());
        }
        return rMap;
    }
}
