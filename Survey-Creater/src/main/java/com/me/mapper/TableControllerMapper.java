package com.me.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

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
}
