package com.afan.service;

import com.afan.entity.User;

/**
 * Create By User on 2020/8/25
 * 业务层接口：UserService
 */
public interface UserService {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    public User findByUserName(String username);

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    public User findById(Integer id);
}
