package com.doris.springbootmall.dao;

import com.doris.springbootmall.dto.UserRegisterRequest;
import com.doris.springbootmall.model.User;

public interface UserDao {

    User getUserById(Integer userId);

    Integer createUser(UserRegisterRequest userRegisterRequest);
}
