package com.me.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.me.beans.ReturnMessage;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    @PostMapping("/createSurvey")
    @ResponseBody
    public ReturnMessage createSurvey(@RequestParam("params")String page, HttpServletRequest request){

        /*        String mypage=page.replaceAll("\\\\n","");
        mypage=mypage.replaceAll(" ","");*/

        page=page.replaceFirst("\"","");
        page=page.substring(0,page.length()-1);
        page= StringEscapeUtils.unescapeJava(page);
        System.out.println(page);
        JSONObject jsonObject= JSONObject.parseObject(page);


        return new ReturnMessage(true);
    }


}

