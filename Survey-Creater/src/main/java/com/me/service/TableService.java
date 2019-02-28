package com.me.service;

import java.util.HashMap;

/**
 * @Description -> TODO
 * @Author -> xufeng
 * @Data ->
 * @about ->
 **/
public interface TableService {
    /**
     * create table
     *
     * @param hashMap map for table
     * @return Result<void>
     */
    void createTable(HashMap hashMap);

    /**
     * dropTable
     *
     * @param tableName tableName
     * @return Result<void>
     */
    void dropTable(String tableName);

    void insertData(HashMap hashMap,String tableName);
}
