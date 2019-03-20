package com.me.aspectj;

import com.me.vo.ReturnMessage;
import com.me.security.TokenCreater;
import com.me.utils.RedisUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName TokenAspectJ
 * @Description 在用户或管理员的请求前对token进行验证
 * @Author xufeng
 * @Data 2019/3/1 18:05
 * @Version 1.0
 **/
@Aspect
@Component
public class TokenAspectJ {

    @Pointcut("execution(public * com.me.controller.UserController.*(..))")
    private void aop1(){}

    @Pointcut("execution(public * com.me.controller.ManagerController.*(..))")
    private void aop2(){}

    @Pointcut("execution(public * com.me.controller.ResultController.*(..))")
    private void aop3(){}

    @Autowired
    private RedisUtil redisUtil;

    @Around("aop1() || aop2() || aop3()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] obj = joinPoint.getArgs();
        Object result;
        String token = (String) obj[obj.length-1];
        if (redisUtil.get((String) TokenCreater.parseJWT(token).get("id")).equals(token)){
            result = joinPoint.proceed();
            return result;
        }
        return new ReturnMessage(true,401);
    }

}
