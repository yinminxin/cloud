package com.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.base.BaseController;
import com.common.result.ResponseVO;
import com.entity.User;
import com.netflix.discovery.converters.Auto;
import com.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 用户控制层
 * @author: yinminxin
 * @create: 2020-11-06
 **/
@RestController
@RequestMapping("userCon")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping("getUserList")
    public ResponseVO getUserList(){
        List<User> userList = userService.getUserList();
        return getFromData(userList);
    }

    @PostMapping("hasPassPermission")
    public ResponseVO hasPassPermission(@RequestBody String str){
        if (StringUtils.isNotBlank(str)) {
            return getSuccess();
        }
        //用户token
        String token = null;
        //接口路径
        String url = null;
        if (StringUtils.isNotBlank(str)) {
            //解析json字符串
            JSONObject jsonObject = JSON.parseObject(str);
            try {
                if(StringUtils.isNotBlank(jsonObject.getString("url"))){
                    url = jsonObject.getString("url");
                }
                if(StringUtils.isNotBlank(jsonObject.getString("token"))){
                    token = jsonObject.getString("token");
                }
            } catch (NumberFormatException e) {
                return getFailure();
            }
        }
        if (StringUtils.isEmpty(url)) {
            return getFailure();
        }
        Boolean result = userService.hasPassPermission(url, token);
        if (result) {
            return getSuccess();
        }
        return getFailure();
    }
}
