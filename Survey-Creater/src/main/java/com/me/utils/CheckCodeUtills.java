package com.me.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * @ClassName CheckCodeUtills
 * @Description TODO
 * @Author xufeng
 * @Data 2019/2/22 11:03
 * @Version 1.0
 **/
public class CheckCodeUtills {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckCodeUtills.class);
    public static String makeNum() {
        Random r = new Random();
        String num = r.nextInt(999999) + "";
        LOGGER.info("验证码:"+num);
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < 6 - num.length(); i++) {
            sb.append("0");
        }
        num = sb.toString() + num;
        return num;
    }
}
