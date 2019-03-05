package com.me.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.me.beans.Page;
import com.me.beans.ReturnMessage;
import com.me.beans.Surveys;
import com.me.service.SurveysService;
import com.me.service.TableService;
import com.me.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

        Page page = tableService.selectPage(pageId, (HashMap) myMap,current);
        if(page == null){
            return new ReturnMessage(true,true);
        }
        return new ReturnMessage(true, page);
    }

    @GetMapping("/selectAll")
    @ResponseBody
    public ReturnMessage selectAll(@RequestParam("pageId") String pageId , @RequestHeader("Authorization")String token){


        return new ReturnMessage(true);
    }

    @DeleteMapping("/deleteSurvey")
    @ResponseBody
    public ReturnMessage deleteSurvey(@RequestParam("pageId") String pageId,@RequestHeader("Authorization")String token){
        surveysService.delete(new EntityWrapper<Surveys>().eq("pageid",pageId));
        tableService.dropTable(pageId);
        redisUtil.del(pageId);
        return new ReturnMessage(true,"删除成功");
    }


}
