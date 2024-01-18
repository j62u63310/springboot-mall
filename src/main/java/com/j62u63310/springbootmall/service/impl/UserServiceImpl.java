package com.j62u63310.springbootmall.service.impl;

import com.j62u63310.springbootmall.dao.UserDao;
import com.j62u63310.springbootmall.dto.UserLoginRequest;
import com.j62u63310.springbootmall.dto.UserRegisterRequest;
import com.j62u63310.springbootmall.model.User;
import com.j62u63310.springbootmall.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.logging.Logger;

@Component
public class UserServiceImpl implements UserService {


    private static final Logger log = Logger.getLogger(UserServiceImpl.class.getName());

    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());
        if(user!=null){
            log.warning("該email " + userRegisterRequest.getEmail() + " 已經被註冊了");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        String hashedPassword = DigestUtils.md5DigestAsHex(userRegisterRequest.getPassword().getBytes());
        userRegisterRequest.setPassword(hashedPassword);

        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {
        User user = userDao.getUserByEmail(userLoginRequest.getEmail());
        if(user==null){
            log.warning("該email " + userLoginRequest.getEmail() + " 尚未被註冊");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        String hashedPassword = DigestUtils.md5DigestAsHex(userLoginRequest.getPassword().getBytes());

        if(user.getPassword().equals(hashedPassword)){
            return user;
        }else{
            log.warning("email " + userLoginRequest.getEmail() + " 的密碼不正確");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
