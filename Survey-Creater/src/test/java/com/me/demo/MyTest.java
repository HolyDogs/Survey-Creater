package com.me.demo;

import com.me.beans.User;
import com.me.mapper.TableControllerMapper;
import com.me.mapper.UserMapper;
import com.me.security.Md5Utils;
import com.me.service.TableService;
import com.me.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * @ClassName MyTest
 * @Description TODO
 * @Author xufeng
 * @Data 2019/2/22 13:46
 * @Version 1.0
 **/
public class MyTest extends DemoApplicationTests{

 /*   @Autowired
    UserService userService;

    @Test
    public void test() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        User user=new User();
        user.setEmail("xf@qq.com");
        user.setIdentify(1);
        user.setName("小强");
        user.setPassword(Md5Utils.EncodePassword("123"));
        userService.insert(user);
    }*/

    @Autowired
    TableService tableService;

    @Autowired
    TableControllerMapper tableControllerMapper;

    @Test
    public void myTest(){

/*        tableService.dropTable("dsafakshfdksahlfhdashdfh");*/
        HashMap<String,String> mymap = new HashMap<>();
        mymap.put("theTableName","dsafakshfdksahlfhdashdfh");
        mymap.put("text","text");
        mymap.put("username","varchar(10)");
        mymap.put("ididiid","int");
        tableService.createTable(mymap);

        HashMap<String ,String> dataMap = new HashMap<>();
        dataMap.put("text","哈哈哈");
        dataMap.put("username","tomtom");
        String tableName = "dsafakshfdksahlfhdashdfh";
        tableControllerMapper.insertData(dataMap,tableName);

    }
}
