package com.doris.springbootmall.service.impl;

import com.doris.springbootmall.dao.UserDao;
import com.doris.springbootmall.dto.UserLoginRequest;
import com.doris.springbootmall.dto.UserRegisterRequest;
import com.doris.springbootmall.model.User;
import com.doris.springbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {

    // 基本上只有括號的值會變動
    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {

        // 檢查註冊的 email
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());


        // user != null => email 已經註冊過帳號，前端請求需要被停止
        // log裡面的 {} 帶入參數值
        if (user != null) {
            log.warn("該 email {} 已經被 {}  註冊", userRegisterRequest.getEmail(), "Judy");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 使用 MD5 生成密碼的雜湊值
        String hashPassword = DigestUtils.md5DigestAsHex(userRegisterRequest.getPassword().getBytes());
        userRegisterRequest.setPassword(hashPassword);



        // 創建帳號
        return userDao.createUser(userRegisterRequest);
    }

    /**
     * 使用者輸入的帳號密碼是否和資料庫中所儲存的user數據是完全一致的
     */
    @Override
    public User login(UserLoginRequest userLoginRequest) {

        // 去資料庫中查詢該筆數據出來
        User user = userDao.getUserByEmail(userLoginRequest.getEmail());


        // 檢查 USER 是否存在
        if (user == null) {
            log.warn("該 email {} 尚未註冊", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }


        // 使用 MD5 生成密碼雜湊值
        String hashPassword = DigestUtils.md5DigestAsHex(userLoginRequest.getPassword().getBytes());
        userLoginRequest.setPassword(hashPassword);


        // 比較密碼 (雜湊值是否相同)
        if (user.getPassword().equals(hashPassword)) {
            return user;
        }else{
            log.warn("email {} 的密碼不正確", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
