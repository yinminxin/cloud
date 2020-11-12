package com.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.common.base.BaseController;
import com.common.result.ResponseVO;
import com.service.UserClientService;
import com.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 用户控制层
 * @author: yinminxin
 * @create: 2020-11-06
 **/
@RestController
@RequestMapping("userCon")
public class UserController extends BaseController {

    @Autowired
    private UserClientService userClientService;

    @GetMapping("getUserList")
    public ResponseVO getUserList(){

        List<UserVO> userList = userClientService.getUserList();
        return getFromData(userList);
    }

    @GetMapping("hasPassPermission")
    public ResponseVO hasPassPermission(){
        String token = request.getHeader("token");
        String requestURI = request.getRequestURI();
        Map<String, String> map = new HashMap<>();
        map.put("token",token);
        map.put("url",requestURI);
        String param = JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
        ResponseVO responseVO = userClientService.hasPassPermission(param);
        return responseVO;
    }
}
