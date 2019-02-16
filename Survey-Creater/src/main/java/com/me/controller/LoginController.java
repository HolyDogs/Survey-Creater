package com.me.controller;

import com.me.beans.ReturnMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class LoginController {

    @RequestMapping("/login")
    @ResponseBody
    public HashMap login(@RequestParam("email")String email, @RequestParam("password")String password){
        System.out.println("I AM COMING!============");
        System.out.println(email);
        System.out.println(password);

        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("success",true);
        hashMap.put("islogin",false);
        hashMap.put("ismanager",true);
        hashMap.put("email","");
        hashMap.put("name","");

        return hashMap;
        /*writer.write("success!!");*/
    }

    @RequestMapping("/sendcode")
    @ResponseBody
    public ReturnMessage sendcode(@RequestParam("email")String email){
        System.out.println(email);
        return new ReturnMessage(true);
    }
}
