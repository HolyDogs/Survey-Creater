package com.me.mapper;

import com.me.beans.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xff
 * @since 2019-02-22
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * fetch data by rule id
     *
     * @param email
     * @return Result<User>
     */
    User selectByEmail(String email);
}
