package com.me.aspectj;

import com.me.security.TokenCreater;
import com.me.utils.RedisUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @ClassName HistoryAspectJ
 * @Description TODO
 * @Author xufeng
 * @Data 2019/3/11 9:17
 * @Version 1.0
 **/
@Aspect
@Component
public class HistoryAspectJ {

    @Pointcut("execution(public * com.me.controller.LoginController.for*(..))")
    private void po1(){}

    @Pointcut("execution(public * com.me.controller.ManagerController.deleteUser(..))")
    private void po2(){}

    @Pointcut("execution(public * com.me.controller.UserController.*(..))")
    private void po3(){}

    @Pointcut("execution(public * com.me.controller.ResultController.deleteSurvey(..))")
    private void po4(){}

    @Autowired
    private RedisUtil redisUtil;

    private final static String TIMELINE = "TIMELINE";

    @Before("po1()||po2()||po3()||po4()")
    public void doBefore(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        LocalDateTime now = LocalDateTime.now();
        String time = now.toString();
        redisUtil.hset(TIMELINE, time, methodName);
        Object[] args = joinPoint.getArgs();
        redisUtil.hset(time,"time",time);
        redisUtil.hset(time,"img","static/time.jpg");
        switch (methodName){
            case "forLogin":
                redisUtil.hset(time,"title",args[0]);
                redisUtil.hset(time,"content","请求登陆");
                break;
            case "forRegister":
                redisUtil.hset(time,"title",args[0]);
                redisUtil.hset(time,"content","请求注册");
                break;
            case "deleteUser":
                redisUtil.hset(time,"title","管理员");
                redisUtil.hset(time,"content","删除id="+args[0]+"的用户");
                break;
            case "createSurvey":
                redisUtil.hset(time,"title", TokenCreater.parseJWT((String) args[3]).get("id"));
                redisUtil.hset(time,"content","创建调查页面id="+args[1]);
                break;
            case "deleteSurvey":
                redisUtil.hset(time,"title",TokenCreater.parseJWT((String) args[1]).get("id"));
                redisUtil.hset(time,"content","删除调查id="+args[0],259200);
                break;
        }
    }
}
