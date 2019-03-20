package com.me.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.me.vo.ReturnMessage;
import com.me.po.Surveys;
import com.me.po.User;
import com.me.service.SurveysService;
import com.me.service.TableService;
import com.me.service.UserService;
import com.me.utils.JSONStrUtils;
import com.me.utils.MysqlTypeTransferUtils;
import com.me.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


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
    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/createSurvey")
    @ResponseBody
    public ReturnMessage createSurvey(@RequestParam("params")String page,@RequestParam("pageid")String pageid, HttpSession session ,@RequestHeader("Authorization")String token){
        final String PAGEID_STR = "pageid";
        final String USERID_STR = "userid";
        page= JSONStrUtils.forJsonStr(page);

        if(surveysService.selectOne(new EntityWrapper<Surveys>().eq(PAGEID_STR,pageid)) != null){
            return new ReturnMessage(true,false);
        }

        Integer sessionUserId=(Integer) session.getAttribute(USERID_STR);

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

        HashMap<String, String> surveyMap = JSONStrUtils.forSurveyMessage(page);
        Iterator iterator = surveyMap.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            if(!entry.getValue().equals("html") && !entry.getValue().equals("expression") && !entry.getValue().equals("panel")){
                redisUtil.hset(pageid, (String) entry.getKey(),entry.getValue());
            }
        }

        HashMap mysqlMap = MysqlTypeTransferUtils.transfer(surveyMap);
        mysqlMap.put("theTableName",pageid);
        tableService.createTable(mysqlMap);

        return new ReturnMessage(true, true);
    }


}

