package com.service.impl;

import com.common.result.ResponseVO;
import com.feign.PermissionPass;
import com.service.UserClientService;
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
    private PermissionPass permissionPass;


    @Override
    public ResponseVO hasPassPermission(String param) {
        return permissionPass.hasPassPermission(param);
    }
}
