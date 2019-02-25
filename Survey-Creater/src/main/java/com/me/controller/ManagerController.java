package com.me.controller;

import com.me.beans.ReturnMessage;
import com.me.beans.User;
import com.me.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description -> pageManager
 * @Author -> xufeng
 * @Data ->
 * @about ->
 **/

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/addUser")
    public ReturnMessage addUser(@RequestParam("user") User user){
        userService.insert(user);
        return new ReturnMessage(true);
    }


}
