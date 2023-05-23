package com.doris.springbootmall.service;

import com.doris.springbootmall.dto.UserRegisterRequest;
import com.doris.springbootmall.model.User;

public interface UserService {

    User getUserById(Integer userId);

    Integer register(UserRegisterRequest userRegisterRequest);

}
