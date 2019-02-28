package com.me.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.me.beans.ReturnMessage;
import com.me.beans.Surveys;
import com.me.service.SurveysService;
import com.me.service.TableService;
import com.me.utils.JSONResultFormatterUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xff
 * @since 2019-02-26
 */
@RestController
@RequestMapping("/surveys")
public class SurveysController {

    @Autowired
    private SurveysService surveysService;
    @Autowired
    private TableService tableService;

    @GetMapping("/page")
    @ResponseBody
    public ReturnMessage returnPage(@RequestParam("pageId")String pageId){
        Surveys surveys = surveysService.selectOne(new EntityWrapper<Surveys>().eq("pageid",pageId));
        if(surveys == null){
            return new ReturnMessage(false);
        }
/*        String data =  StringEscapeUtils.escapeJava(surveys.getContent()) ;*/
        String data = surveys.getContent();
        return new ReturnMessage(true,data);
    }

    @PostMapping("/commit")
    @ResponseBody
    public ReturnMessage commitResult(@RequestParam("params")String result,@RequestParam("tableId") String tableId){
        HashMap rMap = JSONResultFormatterUtils.forResult(result);
        tableService.insertData(rMap,tableId);
        return new ReturnMessage(true);
    }
}

