package com.me.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.me.vo.ManageReturn;
import com.me.vo.ReturnMessage;
import com.me.po.User;
import com.me.service.UserService;
import com.me.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @Autowired
    private RedisUtil redisUtil;

    @DeleteMapping("/deleteUser")
    @ResponseBody
    public ReturnMessage deleteUser(@RequestParam("userId")Integer userId,@RequestHeader("Authorization")String token){
        userService.deleteById(userId);
        return new ReturnMessage(true);
    }

    @GetMapping("/selectPage")
    @ResponseBody
    public ManageReturn findPages(@RequestParam("current")Integer current,@RequestParam(value = "email")String email,@RequestParam("identify")Integer identify,@RequestParam("id")Integer id,@RequestParam("name")String name,@RequestHeader("Authorization")String token){
        EntityWrapper<User> entityWrapper = new EntityWrapper();
        if (id!=null){
            entityWrapper.eq("id",id);
        }
        if (email!=null||!email.equals("")){
            entityWrapper.like("email",email);
        }
        if (identify!=null){
            entityWrapper.eq("identify",identify);
        }
        if (name!=null||!name.equals("")) {
            entityWrapper.like("name",name);
        }
        Page page = userService.selectPage(new Page<>(current,6),entityWrapper);
        return new ManageReturn(true,page);
    }


    @GetMapping("/getHistory")
    @ResponseBody
    public ManageReturn getHistory(@RequestHeader("Authorization")String token){
        List<Map<Object, Object>> dList = new ArrayList<>();
        HashMap hashMap = (HashMap) redisUtil.hmget("TIMELINE");
        Iterator iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            dList.add(redisUtil.hmget(entry.getKey().toString()));
        }
        return new ManageReturn(true,dList);
    }
}
