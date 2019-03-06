package com.me.service.impl;

import com.me.beans.Page;
import com.me.mapper.TableControllerMapper;
import com.me.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName TableServiceImpl
 * @Description TODO
 * @Author xufeng
 * @Data 2019/2/27 22:31
 * @Version 1.0
 **/

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableControllerMapper tableMapper;

    @Override
    public void createTable(HashMap hashMap) {
        Integer flag = tableMapper.existTable((String) hashMap.get("theTableName"));
        if (flag > 0){
            return;
        }
        tableMapper.createNewTable(hashMap);
    }

    @Override
    public void dropTable(String tableName) {
        Integer flag = tableMapper.existTable(tableName);
        if (flag > 0){
            tableMapper.dropTable(tableName);
        }
    }

    @Override
    public void insertData(HashMap hashMap, String tableName) {
        Integer flag = tableMapper.existTable(tableName);
        if (flag > 0){
            tableMapper.insertData(hashMap,tableName);
        }
    }

    @Override
    public Page selectPage(String tableName, HashMap headMap, int current) {
        Integer flag = tableMapper.existTable(tableName);
        if (flag <= 0){
            return null;
        }
        Page page = new Page();

        int size = 8;
        int total = tableMapper.selectTotal(tableName);
        int pages = total/size + 1;
        Boolean hasPre = false;
        Boolean hasNext = false;
        ArrayList<HashMap<String, Object>> list;
        int start = (current-1)*size;
        int over = total-start;

        if (current>1 && pages>1){
            hasPre = true;
        }
        if(current<pages){
            hasNext = true;
            list = (ArrayList<HashMap<String, Object>>) tableMapper.selectPage(tableName,headMap,start,size);
        }else {
            list = (ArrayList<HashMap<String, Object>>) tableMapper.selectPage(tableName,headMap,start,over);
        }

        page.setCurrent(current);
        page.setHasNext(hasNext);
        page.setHasPrevious(hasPre);
        page.setList(list);
        page.setPages(pages);
        page.setSize(size);
        page.setTotal(total);

        return page;
    }
}
