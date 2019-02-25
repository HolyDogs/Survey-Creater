package com.me.security;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName MD5Utils
 * @Description TODO
 * @Author xufeng
 * @Data 2019/2/22 13:07
 * @Version 1.0
 **/
public class Md5Utils {
    private static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder=new BASE64Encoder();

        String newstr=base64Encoder.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }

    public static String EncodePassword(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String str=password+"xufeng";
        return EncoderByMd5(EncoderByMd5(EncoderByMd5(str)));
    }

}
