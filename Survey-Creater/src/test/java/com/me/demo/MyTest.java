package com.me.demo;

import com.me.po.User;
import com.me.mapper.TableControllerMapper;
import com.me.security.Md5Utils;
import com.me.service.TableService;
import com.me.service.UserService;
import org.apache.commons.lang.StringEscapeUtils;
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
        for (int i=77;i<177;i++){
            user.setEmail("testEmail"+i+"@test.com");
            user.setIdentify(1);
            user.setName("测试用户"+i+"号");
            user.setPassword(Md5Utils.EncodePassword("123"));
            userService.insert(user);
        }
    }

    @Autowired
    TableService tableService;

    @Autowired
    TableControllerMapper tableControllerMapper;

    @Test
    public void myTest(){

/*        tableService.dropTable("dsafakshfdksahlfhdashdfh");*/

/*        HashMap<String,String> mymap = new HashMap<>();
        mymap.put("theTableName","dsafakshfdksahlfhdashdfh");
        mymap.put("text","text");
        mymap.put("username","varchar(10)");
        mymap.put("ididiid","int");
        tableService.createTable(mymap);

        HashMap<String ,String> dataMap = new HashMap<>();
        dataMap.put("text","哈哈哈");
        dataMap.put("username","tomtom");
        String tableName = "dsafakshfdksahlfhdashdfh";
        tableControllerMapper.insertData(dataMap,tableName);*/

/*        HashMap<String,Object> hashMap = new HashMap();
        hashMap.put("id",null);
        hashMap.put("name",null);
        hashMap.put("email",null);
        List list = tableControllerMapper.selectPage("user",hashMap,1,3);
        list.stream().forEach(System.out::println);*/

/*        int i = 0;
        int d = 99;
        while (i<20) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("question1", i++);
            hashMap.put("question2", d-- + "哈哈哈");

            tableService.insertData(hashMap, "4f9a79ea26010ea5bbd65169ef483dd8");
        }*/

        String str = "\"pages\"";
        str = StringEscapeUtils.escapeJava(str);
        System.out.println(str);
        System.out.println(
        tableControllerMapper.selectCountLikeItem("surveys","content",str)
        );
    }
}
