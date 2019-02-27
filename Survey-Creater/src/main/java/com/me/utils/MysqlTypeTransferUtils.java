package com.me.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @ClassName MysqlTypeTransferUtils
 * @Description TODO
 * @Author xufeng
 * @Data 2019/2/27 11:49
 * @Version 1.0
 **/
public class MysqlTypeTransferUtils {

    public static HashMap transfer(HashMap<String,String> map){
        HashMap<String,String> newMap = new HashMap<>(64);
        Iterator iterator = map.entrySet().iterator();
        if (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            switch ((String)entry.getValue()){
                case "text":
                case "radiogroup":
                case "dropdown":
                case "comment":
                case "rating":
                case "imagepicker":
                case "matrix":
                case "matrixdynamic":
                case "matrixdropdown":
                case "multipletext":
                case "sortablelist":
                case "checkbox":
                    newMap.put((String) entry.getKey(),"VARCHAR(100)");
                    break;
                case "file":
                case "signaturepad":
                    newMap.put((String) entry.getKey(),"TEXT");
                    break;
                case "boolean":
                    newMap.put((String) entry.getKey(),"TINYINT(1)");
                    break;
                case "nouislider":
                    break;
                default:
                    break;
            }
        }
        return null;
    }
}
