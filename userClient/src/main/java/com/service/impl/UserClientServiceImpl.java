package com.service.impl;

import com.common.result.ResponseVO;
import com.feign.UserService;
import com.service.UserClientService;
import com.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 用户业务层
 * @author: yinminxin
 * @create: 2020-11-06
 **/
@Service
public class UserClientServiceImpl implements UserClientService {

//    @Autowired
//    private RestTemplate restTemplate;
    @Autowired
    private UserService userService;

    @Override
    public List<UserVO> getUserList() {
//        ResponseVO forEntity = restTemplate.getForObject("http://user-service:9010/user/userCon/getUserList", ResponseVO.class);
        ResponseVO forEntity = userService.getUserList();
        System.out.println(forEntity);
        List<UserVO> data = (List<UserVO>) forEntity.getData();
        return data;
    }

    @Override
    public ResponseVO hasPassPermission(String param) {
        return userService.hasPassPermission(param);
    }
}
