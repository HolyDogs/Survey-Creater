package com.me.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @author fenghere
 */
@Mapper
public interface TableControllerMapper {
    /**
     * fetch data by rule id
     *
     * @param map mymap
     * @return Result<null>
     */
    void createNewTable(@Param(value = "myMap") HashMap map);

    /**
     * fetch data by rule id
     *
     * @param name tableName
     * @return Result<Integer>
     */
    void dropTable(@Param(value = "tablename") String name);

    /**
     * fetch data by rule id
     *
     * @param tableName tableName
     * @return Result<Integer>
     */
    Integer existTable(@Param(value = "tableName") String tableName);

    /**
     * fetch data by rule id
     *
     * @param dataMap map for data
     * @param tableName name of table
     * @return Result<void>
     */
    void insertData(@Param(value = "dataMap") HashMap dataMap, @Param(value = "tableName") String tableName);

    /**
     * fetch data by rule id
     *
     * @param headMap map for head
     * @param start start page
     * @param tableName name of table
     * @param finish finish page
     * @return Result<void>
     */
    List<HashMap<String,Object>> selectPage(@Param(value = "tableName")String tableName, @Param(value = "myMap") HashMap headMap, @Param(value = "start") int start,@Param(value = "finish") int finish);

    /**
     * fetch data by rule id
     *
     * @param tableName name of table
     * @return Result<void>
     */
    Integer selectTotal(@Param(value = "tableName") String tableName);

    /**
     * fetch data by rule id
     *
     * @param tableName name of table
     * @param item
     * @param column
     * @return Result<void>
     */
    Integer selectItemCount(@Param(value = "tableName")String tableName ,@Param(value = "column")String column, @Param(value = "item")String item);

    /**
     * fetch data by rule id
     *
     * @param tableName name of table
     * @param column
     * @return Result<void>
     */
    Integer selectItemCountIsNull(@Param(value = "tableName")String tableName ,@Param(value = "column")String column);


    /**
     * fetch data by rule id
     *
     * @param tableName name of table
     * @param item
     * @param column
     * @return Result<void>
     */
    Integer selectCountLikeItem(@Param(value = "tableName")String tableName , @Param(value = "column")String column,@Param(value = "item")String item);
}
