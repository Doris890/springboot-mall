package com.doris.springbootmall.service.impl;

import com.doris.springbootmall.dao.UserDao;
import com.doris.springbootmall.dto.UserRegisterRequest;
import com.doris.springbootmall.model.User;
import com.doris.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

@Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {


        return userDao.createUser(userRegisterRequest);
    }
}
