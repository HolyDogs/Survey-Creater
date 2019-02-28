package com.me.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.me.beans.ReturnMessage;
import com.me.beans.Surveys;
import com.me.beans.User;
import com.me.service.SurveysService;
import com.me.service.TableService;
import com.me.service.UserService;
import com.me.utils.JSONStrUtils;
import com.me.utils.MysqlTypeTransferUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xff
 * @since 2019-02-22
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SurveysService surveysService;
    @Autowired
    private UserService userService;
    @Autowired
    private TableService tableService;

    @PostMapping("/createSurvey")
    @ResponseBody
    public ReturnMessage createSurvey(@RequestParam("params")String page,@RequestParam("pageid")String pageid, HttpSession session){

        /*        String mypage=page.replaceAll("\\\\n","");
        mypage=mypage.replaceAll(" ","");*/

        page= JSONStrUtils.forJsonStr(page);

        System.out.println(page);


/*
        //测试map里的值
        Iterator iterator = surveyMap.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            System.out.println(entry.getKey()+"============"+entry.getValue());
        }*/

        if(surveysService.selectOne(new EntityWrapper<Surveys>().eq("pageid",pageid)) != null){
            return new ReturnMessage(true,false);
        }

        Integer sessionUserId=(Integer) session.getAttribute("userid");

        if(sessionUserId==null){
            return new ReturnMessage(true,false);
        }

        Surveys surveys=new Surveys();
        surveys.setUserid(sessionUserId);
        surveys.setPageid(pageid);
        surveys.setContent(page);
        surveysService.insert(surveys);

        User user = userService.selectById(sessionUserId);
        user.setPossess(1);
        userService.updateById(user);

        HashMap surveyMap = JSONStrUtils.forSurveyMessage(page);
        HashMap mysqlMap = MysqlTypeTransferUtils.transfer(surveyMap);
        mysqlMap.put("theTableName",pageid);
        tableService.createTable(mysqlMap);

        return new ReturnMessage(true, true);
    }
}

