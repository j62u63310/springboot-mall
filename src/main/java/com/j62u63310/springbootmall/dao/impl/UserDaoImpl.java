package com.j62u63310.springbootmall.dao.impl;

import com.j62u63310.springbootmall.dao.UserDao;
import com.j62u63310.springbootmall.dto.UserRegisterRequest;
import com.j62u63310.springbootmall.model.Product;
import com.j62u63310.springbootmall.model.User;
import com.j62u63310.springbootmall.rowmapper.ProductRowMapper;
import com.j62u63310.springbootmall.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserDaoImpl implements UserDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createUser(UserRegisterRequest userRegisterRequest) {
        String sql = "INSERT INTO consumer (email,password,created_date,last_modified_date) " +
                "VALUES (:email, :password, :createdDate, :lastModifiedDate)";

        Date date = new Date();
        Map<String ,Object> map = new HashMap<>();
        map.put("email",userRegisterRequest.getEmail());
        map.put("password",userRegisterRequest.getPassword());
        map.put("createdDate",date);
        map.put("lastModifiedDate",date);


        KeyHolder keyHolder = new GeneratedKeyHolder();


        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);


        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public User getUserById(Integer userId) {
        String sql = "SELECT user_id, email, password, created_date, last_modified_date " +
                "FROM consumer WHERE user_id = :userId";
        Map<String ,Object> map = new HashMap<>();
        map.put("userId",userId);
        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        if(!userList.isEmpty()) return userList.get(0);
        else return null;
    }

    @Override
    public User getUserByEmail(String email) {
        String sql = "SELECT user_id, email, password, created_date, last_modified_date " +
                "FROM consumer WHERE email = :email";
        Map<String ,Object> map = new HashMap<>();
        map.put("email",email);
        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        if(!userList.isEmpty()) return userList.get(0);
        else return null;
    }
}
