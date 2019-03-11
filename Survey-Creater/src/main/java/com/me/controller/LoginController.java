package com.me.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.me.config.EmailMessage;
import com.me.beans.ReturnMessage;
import com.me.beans.User;
import com.me.security.Md5Utils;
import com.me.security.TokenCreater;
import com.me.service.UserService;
import com.me.utils.CheckCodeUtills;
import com.me.utils.RedisUtil;
import com.me.utils.SendEmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * @Description -> login and register page
 * @Author -> xufeng
 * @Data ->
 * @about ->
 **/

@RestController
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/login")
    @ResponseBody
    public HashMap forLogin(@RequestParam("email")String email, @RequestParam("password")String password,HttpSession session) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        System.out.println(email);
        User user=userService.loginCheck(email,password);
        HashMap<String,Object> hashMap=new HashMap<>(16);

        if (user==null){
            hashMap.put("success",true);
            hashMap.put("islogin",false);
            hashMap.put("ismanager",false);
            return hashMap;
        }
        hashMap.put("success",true);
        hashMap.put("islogin",true);
        hashMap.put("ismanager",user.getIdentify().equals(0));
        hashMap.put("email",user.getEmail());
        hashMap.put("name",user.getName());
        session.setAttribute("userid",user.getId());

        //创建token返回并存入redis数据库，设置过期时间为1天
        String token = TokenCreater.createJWT(email,user.getPassword(),user.getName(),86400000);
        redisUtil.set(email,token,86400);

        hashMap.put("token",token);
        return hashMap;
        /*writer.write("success!!");*/
    }

    @RequestMapping("/sendcode")
    @ResponseBody
    public ReturnMessage sendcode(@RequestParam("email")String email, HttpSession httpSession){
        String checkCode= CheckCodeUtills.makeNum();
        httpSession.setAttribute("checkCode",checkCode);
        httpSession.setMaxInactiveInterval(3*60);
        SendEmailUtils.sendEmails(EmailMessage.getEmailBody()+checkCode,email);
        return new ReturnMessage(true);
    }

    @RequestMapping("/register")
    @ResponseBody
    public HashMap forRegister(@RequestParam("email")String email,@RequestParam(value = "name",required = false,defaultValue = "未取名")String name,@RequestParam("password")String password,
                         @RequestParam("checkcode")String checkcode,HttpSession httpSession) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        HashMap<String,Object> result=new HashMap<>(16);
        String sessionStr = "checkCode";
        String emailStr = "email";
        result.put("success",true);
        if(!checkcode.equals(httpSession.getAttribute(sessionStr))){
            result.put("message","验证码错误");
            result.put("isRegisted",false);
            return result;
        }

        if(userService.selectOne(new EntityWrapper<User>().eq(emailStr,email))!=null){
            result.put("message","该email已注册");
            result.put("isRegisted",false);
            return result;

        }

        userService.insert(new User(name,1,email, Md5Utils.EncodePassword(password)));
        result.put("isRegisted",true);
        return result;

    }
}
