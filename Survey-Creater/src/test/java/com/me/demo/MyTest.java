package com.me.demo;

import com.me.beans.User;
import com.me.security.Md5Utils;
import com.me.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName MyTest
 * @Description TODO
 * @Author xufeng
 * @Data 2019/2/22 13:46
 * @Version 1.0
 **/
public class MyTest extends DemoApplicationTests{

    @Autowired
    UserService userService;

    @Test
    public void test() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        User user=new User();
        user.setEmail("xf@qq.com");
        user.setIdentify(1);
        user.setName("小强");
        user.setPassword(Md5Utils.EncodePassword("123"));
        userService.insert(user);
    }
}
