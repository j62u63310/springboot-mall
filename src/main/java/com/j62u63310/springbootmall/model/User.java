package com.j62u63310.springbootmall.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer userId;
    private String email;
    private String password;
    private Date createdDate;
    private Date lastModifiedDate;
}
