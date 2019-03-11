package com.me.beans;

import com.baomidou.mybatisplus.plugins.Page;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName ManageReturn
 * @Description TODO
 * @Author xufeng
 * @Data 2019/3/10 14:23
 * @Version 1.0
 **/
public class ManageReturn {
    public boolean success;
    public Page page;
    public List<HashMap<String,Object>> dataList;

    public ManageReturn(boolean flag,Page page){
        this.success = flag;
        this.page = page;
    }

    public ManageReturn(boolean flag,List list){
        this.success = flag;
        this.dataList = list;
    }
}
