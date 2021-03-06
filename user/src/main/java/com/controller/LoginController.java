package com.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.base.BaseController;
import com.common.result.ResponseVO;
import com.config.properties.JwtProperties;
import com.entity.User;
import com.service.UserService;
import com.utils.JwtUtils;
import com.utils.RedisUtils;
import com.vo.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author yinminxin
 * @description 登陆相关控制层
 * @date 2020/1/3 15:57
 */
@Controller
@RequestMapping("/")
public class LoginController extends BaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwt;
    @Autowired
    private RedisUtils redisUtils;

    @PostMapping("/login")
    @ResponseBody
    private ResponseVO testLogin(@RequestBody String str){
        //用户Id
        String userName = null;
        String passWord = null;
        if (StringUtils.isNotBlank(str)) {
            //解析json字符串
            JSONObject jsonObject = JSON.parseObject(str);
            try {
                if(StringUtils.isNotBlank(jsonObject.getString("userName")) && StringUtils.isNotBlank(jsonObject.getString("passWord"))){
                    userName = jsonObject.getString("userName");
                    passWord = jsonObject.getString("passWord");
                }else{
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("请求参数验证不合法，异常信息：【{}】", "参数错误");
                    }
                    return getFailure("参数错误");
                }
            } catch (NumberFormatException e) {
                return getFailure();
            }
        }else {
            return getFailure("参数错误");
        }
        //根据用户名和密码查找用户
        User user = userService.findByUserNameAndPassWord(userName,passWord);
//        User user = new User();
        if (user == null) {
            return getFailure("用户名或密码错误!");
        }
        String token = null;
        try {
            //用户存在生成token
            token = JwtUtils.generateToken(new UserInfo(user.getId(),user.getUserName(),user.getRoleId()), jwt.getPrivateKey(), jwt.getExpire());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (StringUtils.isBlank(token)) {
            return getFailure();
        }
        //将用户信息存入redis
        redisUtils.set(token,user,60L*30);
        //将token存入cookie
//        CookieUtils.setCookie(request,response,jwt.getCookieName(),token,jwt.getCookieMaxAge(),null,true);
        user.setToken(token);
        return getFromData(user);
    }
}
