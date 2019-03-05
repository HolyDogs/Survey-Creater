package com.me.beans;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @ClassName Page
 * @Description TODO
 * @Author xufeng
 * @Data 2019/3/4 9:58
 * @Version 1.0
 **/

@Data
public class Page {
    private int total;
    private ArrayList<HashMap<String,Object>> list;
    private int current;
    private int pages;
    private int size;
    private boolean hasPrevious;
    private boolean hasNext;

}
