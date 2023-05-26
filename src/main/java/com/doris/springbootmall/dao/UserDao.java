package com.doris.springbootmall.dao;

import com.doris.springbootmall.dto.UserRegisterRequest;
import com.doris.springbootmall.model.User;

public interface UserDao {

    User getUserById(Integer userId);


    User getUserByEmail(String email);


    Integer createUser(UserRegisterRequest userRegisterRequest);
}
