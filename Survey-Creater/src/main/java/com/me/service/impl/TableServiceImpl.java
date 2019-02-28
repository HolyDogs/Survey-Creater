package com.me.service.impl;

import com.me.mapper.TableControllerMapper;
import com.me.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

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
}
