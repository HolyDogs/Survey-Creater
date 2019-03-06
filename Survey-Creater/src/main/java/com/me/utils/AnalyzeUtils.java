package com.me.utils;

/**
 * @ClassName AnalyzeUtils
 * @Description TODO
 * @Author xufeng
 * @Data 2019/3/5 16:08
 * @Version 1.0
 **/
public class AnalyzeUtils {

    public static Boolean filterQuestions(String type){
        switch (type){
            case "text":
            case "checkbox":
            case "radiogroup":
            case "dropdown":
            case "rating":
            case "imagepicker":
            case "boolean":
            case "matrix":
            case "matrixdropdown":
            case "matrixdynamic":
                return true;
            case "multipletext":
            case "panel":
            case "paneldynamic":
            case "sortablelist":
            case "comment":
            case "html":
            case "expression":
            case "file":
                return false;
             default:
                 return false;
        }
    }
}
