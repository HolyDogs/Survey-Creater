package com.me.mapper;

import com.me.beans.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xff
 * @since 2019-02-14
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
