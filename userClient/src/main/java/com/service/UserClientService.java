package com.service;

import com.common.result.ResponseVO;
import com.vo.UserVO;

import java.util.List;

/**
 * @description:
 * @author: yinminxin
 * @create: 2020-11-06
 **/
public interface UserClientService {

    List<UserVO> getUserList();

    ResponseVO hasPassPermission(String param);
}
