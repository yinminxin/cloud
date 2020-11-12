package com.service;

import com.entity.User;

import java.util.List;

/**
 * @description:
 * @author: yinminxin
 * @create: 2020-11-06
 **/
public interface UserService {

    List<User> getUserList();

    User findByUserNameAndPassWord(String userName, String passWord);

    Boolean hasPassPermission(String url, String token);
}
