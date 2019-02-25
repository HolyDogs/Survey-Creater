package com.me.service.impl;

import com.me.beans.User;
import com.me.mapper.UserMapper;
import com.me.security.Md5Utils;
import com.me.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xff
 * @since 2019-02-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User loginCheck(String email, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        User user = userMapper.selectByEmail(email);
        if (user == null) {
            return null;
        } else {
            if (userMapper.selectByEmail(email).getPassword().equals(Md5Utils.EncodePassword(password))) {
                return user;
            } else {
                return null;
            }
        }
    }
}
