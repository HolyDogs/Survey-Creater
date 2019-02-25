package com.me.utils;

import java.util.Random;

/**
 * @ClassName CheckCodeUtills
 * @Description TODO
 * @Author xufeng
 * @Data 2019/2/22 11:03
 * @Version 1.0
 **/
public class CheckCodeUtills {
    public static String makeNum() {
        Random r = new Random();

        String num = r.nextInt(999999) + "";
        System.out.println(num);
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < 6 - num.length(); i++) {
            sb.append("0");
        }
        num = sb.toString() + num;
        return num;
    }
}
