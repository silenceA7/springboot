package com.afan.service.impl;

import com.afan.entity.User;
import com.afan.mapper.UserMapper;
import com.afan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create By User on 2020/8/25
 * 业务层实现类：UserServiceImpl
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    @Override
    public User findById(Integer id){
        return userMapper.findById(id);
    };
}
