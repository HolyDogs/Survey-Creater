package com.me.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.me.beans.AnalyzeResult;
import com.me.beans.Page;
import com.me.beans.ReturnMessage;
import com.me.beans.Surveys;
import com.me.service.SurveysService;
import com.me.service.TableService;
import com.me.utils.AnalyzeUtils;
import com.me.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @ClassName ResultController
 * @Description TODO
 * @Author xufeng
 * @Data 2019/3/3 21:25
 * @Version 1.0
 **/
@RestController
public class ResultController {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private TableService tableService;
    @Autowired
    private SurveysService surveysService;

    @GetMapping("/resultGet")
    @ResponseBody
    public ReturnMessage resultGet(@RequestParam("pageId") String pageId ,@RequestParam("current") Integer current, @RequestHeader("Authorization")String token){

        Map<Object, Object> myMap = redisUtil.hmget(pageId);
        if(myMap == null|| "d41d8cd98f00b204e9800998ecf8427e".equals(pageId)){
            return new ReturnMessage(true,true);
        }

        Page page = tableService.selectPage(pageId, (HashMap) myMap,current);
        if(page == null){
            return new ReturnMessage(true,true);
        }
        return new ReturnMessage(true, page);
    }

    @DeleteMapping("/deleteSurvey")
    @ResponseBody
    public ReturnMessage deleteSurvey(@RequestParam("pageId") String pageId,@RequestHeader("Authorization")String token){
        surveysService.delete(new EntityWrapper<Surveys>().eq("pageid",pageId));
        tableService.dropTable(pageId);
        redisUtil.del(pageId);
        return new ReturnMessage(true,"删除成功");
    }

    @GetMapping("/analyze")
    @ResponseBody
    public ReturnMessage analyze(@RequestParam("pageId") String pageId,@RequestHeader("Authorization")String token){
        Map<Object, Object> columns = redisUtil.hmget(pageId);
        List<String> alist = new ArrayList<>(64);
        Iterator itor = columns.entrySet().iterator();
        while (itor.hasNext()){
            Map.Entry entry = (Map.Entry) itor.next();
            if (AnalyzeUtils.filterQuestions(entry.getValue().toString())){
                alist.add(entry.getKey().toString());
            }
        }
        return new ReturnMessage(true,alist);

    }

    @GetMapping("/analyzeQuestion")
    @ResponseBody
    public AnalyzeResult analyzeQuestions(@RequestParam("pageId") String pageId, @RequestParam("question")String question, @RequestHeader("Authorization")String token){

        List<Integer> count ;
        String type = "";
        String title = "";
        String name = "人数";
        List<String> items ;
        List<HashMap<String,Object>> data;
        HashMap<String,Object> bmap;

        String content = surveysService.selectOne(new EntityWrapper<Surveys>().eq("pageid",pageId)).getContent();
        List<JSONObject> jlist = AnalyzeUtils.parsePage(content);
        Iterator itor = jlist.iterator();
        while (itor.hasNext()){
            JSONObject jsonObject = (JSONObject) itor.next();
            if (question.equals(jsonObject.getString("name"))){

                title = (jsonObject.getString("title")==null ? question:jsonObject.getString("title"));
                type = AnalyzeUtils.typeAnalyze(jsonObject.getString("type"));

                if (type.equals("bar")){
                    count = new ArrayList<>(16);
                    items = new ArrayList<>(16);
                    JSONArray jsonArray = jsonObject.getJSONArray("choices");
                    Iterator itorItem = jsonArray.iterator();
                    while(itorItem.hasNext()){
                        Object o = itorItem.next();
                        if (o instanceof JSONObject){
                            String text = ((JSONObject) o).getString("text");
                            if (text == null){
                                items.add(((JSONObject) o).getString("value"));
                            }else {
                                items.add(text);
                            }
                            count.add(tableService.selectItemCount(pageId,question, ((JSONObject) o).getString("value")));
                        }else{
                            items.add(o.toString());
                            count.add(tableService.selectItemCount(pageId,question,o.toString()));
                        }
                    }
                    return new AnalyzeResult(true,count,type,title,name,items);
                }

                if (("pie").equals(type)){
                    data = new ArrayList<>(8);
                    bmap = new LinkedHashMap<>(4);
                    bmap.put("value",tableService.selectItemCount(pageId,question,null));
                    bmap.put("name","未选");
                    data.add(bmap);
                    bmap = new LinkedHashMap<>(4);
                    bmap.put("value",tableService.selectItemCount(pageId,question,"0"));
                    bmap.put("name","否");
                    data.add(bmap);
                    bmap = new LinkedHashMap<>(4);
                    bmap.put("value",tableService.selectItemCount(pageId,question,"1"));
                    bmap.put("name","是");
                    data.add(bmap);

                    return new AnalyzeResult(true,type,title,data);
                }

                if (("rpie").equals(type) || ("dpie").equals(type)){
                    data = new ArrayList<>(16);
                    JSONArray jsonArray = ("rpie").equals(type)?jsonObject.getJSONArray("rateValues"):jsonObject.getJSONArray("choices");
                    Iterator iterator = jsonArray.iterator();
                    while (iterator.hasNext()){
                        Object o = iterator.next();
                        bmap = new LinkedHashMap<>(4);
                        if (o instanceof JSONObject){
                            bmap.put("value",tableService.selectItemCount(pageId,question,((JSONObject) o).getString("value")));
                            bmap.put("name",((JSONObject) o).getString("text"));
                        }else {
                            bmap.put("value",tableService.selectItemCount(pageId,question,o.toString()));
                            bmap.put("name",o.toString());
                        }
                        data.add(bmap);
                    }
                    type = "pie";
                    return new AnalyzeResult(true,type,title,data);
                }

                if(("cpie").equals(type)){
                    data = new ArrayList<>(16);
                    JSONArray jsonArray = jsonObject.getJSONArray("choices");
                    Iterator iterator = jsonArray.iterator();
                    while (iterator.hasNext()){
                        Object o = iterator.next();
                        bmap = new LinkedHashMap<>(4);
                        if (o instanceof JSONObject){
                            bmap.put("value",tableService.selectCountLike(pageId,question,"\""+((JSONObject) o).getString("value")+"\""));
                            bmap.put("name",((JSONObject) o).getString("text"));
                        }else {
                            bmap.put("value",tableService.selectCountLike(pageId,question,"\""+o.toString()+"\""));
                            bmap.put("name",o.toString());
                        }
                        data.add(bmap);
                    }
                    type = "pie";
                    return new AnalyzeResult(true,type,title,data);
                }

                if(("line").equals(type)){
                    items = new ArrayList<>(16);
                    data =  new ArrayList<>(16);
                    JSONArray jsonArray = jsonObject.getJSONArray("columns");
                    JSONArray rowArray = jsonObject.getJSONArray("rows");
                    Iterator rowItor = rowArray.iterator();
                    items.add("columns");
                    while (rowItor.hasNext()){
                        Object o = rowItor.next();
                        items.add(o.toString());
                    }
                    Iterator iterator = jsonArray.iterator();
                    while (iterator.hasNext()){
                        HashMap<String,Object> hashMap = new HashMap<>(16);
                        Object o = iterator.next();
                        for(String str : items){
                            if ("columns".equals(str)){
                                hashMap.put(str,o.toString());
                            }else{
                                hashMap.put(str,tableService.selectCountLike(pageId,question,"\""+str+"\":"+"\""+o.toString()+"\""));
                            }
                        }
                        data.add(hashMap);
                    }
                    return new AnalyzeResult(true,type,title,items,data);
                }
            }
        }
        return new AnalyzeResult(true);
    }



}
