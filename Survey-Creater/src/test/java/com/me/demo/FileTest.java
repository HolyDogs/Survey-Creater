package com.me.demo;

import org.junit.Test;

import java.io.*;
import java.util.Base64;

/**
 * @ClassName FileTest
 * @Description TODO
 * @Author xufeng
 * @Data 2019/3/4 16:38
 * @Version 1.0
 **/
public class FileTest {

    @Test
    public void test() throws IOException {
        String str = "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";
        byte[] file = Base64.getDecoder().decode(str);
        File file1 = new File("E:a.png");
        FileOutputStream fileOutputStream = new FileOutputStream(file1);
        fileOutputStream.write(file);
    }
}
