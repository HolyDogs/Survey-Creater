package com.me.service;

import com.me.beans.Page;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

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

    /**
     * dropTable
     *
     * @param hashMap zz
     * @param tableName tableName
     * @return Result<void>
     */
    void insertData(HashMap hashMap,String tableName);

    /**
     * dropTable
     *
     * @param headMap zz
     * @param tableName tableName
     * @return Result<Page>
     */
    Page selectPage(String tableName, HashMap headMap, int current);

    /**
     * dropTable
     *
     * @param item
     * @param column zz
     * @param tableName tableName
     * @return Result<Page>
     */
    Integer selectItemCount(String tableName,String column,String item);
}
