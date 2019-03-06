package com.me.beans;

/**
 * @ClassName AnalyzeResult
 * @Description TODO
 * @Author xufeng
 * @Data 2019/3/5 18:22
 * @Version 1.0
 **/
public class AnalyzeResult {
    public Boolean success;
    public Integer[] count;
    public String type;
    public String title;
    public String name;
    public String items;
    public AnalyzeResult(Boolean flag){
        this.success = flag;
    }

    public AnalyzeResult(Boolean success, Integer[] count, String type, String title, String name, String items) {
        this.success = success;
        this.count = count;
        this.type = type;
        this.title = title;
        this.name = name;
        this.items = items;
    }
}
