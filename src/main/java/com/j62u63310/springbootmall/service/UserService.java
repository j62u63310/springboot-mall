package com.j62u63310.springbootmall.service;

import com.j62u63310.springbootmall.dto.UserLoginRequest;
import com.j62u63310.springbootmall.dto.UserRegisterRequest;
import com.j62u63310.springbootmall.model.User;

public interface UserService {
    Integer register(UserRegisterRequest userRegisterRequest);
    User getUserById(Integer userId);
    User login(UserLoginRequest userLoginRequest);
}
