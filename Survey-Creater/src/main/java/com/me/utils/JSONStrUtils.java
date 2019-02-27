package com.me.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringEscapeUtils;

import java.util.HashMap;
import java.util.Iterator;

/**
 * @ClassName JSONStrUtils
 * @Description TODO
 * @Author xufeng
 * @Data 2019/2/27 9:26
 * @Version 1.0
 **/
public class JSONStrUtils {

    public static String forJsonStr(String page){
        page=page.replaceFirst("\"","");
        page=page.substring(0,page.length()-1);
        page= StringEscapeUtils.unescapeJava(page);
        return page;
    }

    public static HashMap forSurveyMessage(String page){
        HashMap<String,String> nameMap = new HashMap<>(64);
        JSONObject jsonObject= JSONObject.parseObject(page);
        JSONArray jsonArray = JSONArray.parseArray(jsonObject.getString("pages"));
        Iterator iterator = jsonArray.iterator();
        while (iterator.hasNext()){
            JSONObject pages=(JSONObject) iterator.next();
            if(pages.getString("elements")==null){
                continue;
            }
            JSONArray elements = pages.getJSONArray("elements");
            Iterator elementIte = elements.iterator();
            while (elementIte.hasNext()){
                JSONObject element = (JSONObject) elementIte.next();
                String name = element.getString("name");
                String type = element.getString("type");
                nameMap.put(name, type);
            }
        }
        return nameMap;
    }
}
