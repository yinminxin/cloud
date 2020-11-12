package com.service.impl;

import com.common.constant.RedisTag;
import com.config.properties.JwtProperties;
import com.entity.InterfaceWhite;
import com.entity.User;
import com.mapper.InterfaceWhiteMapper;
import com.mapper.RelUserRoleMapper;
import com.mapper.RoleMapper;
import com.mapper.UserMapper;
import com.netflix.discovery.converters.Auto;
import com.service.UserService;
import com.utils.JwtUtils;
import com.utils.RedisUtils;
import com.vo.RolePermissionListVo;
import com.vo.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @description: 用户业务层
 * @author: yinminxin
 * @create: 2020-11-06
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RelUserRoleMapper relUserRoleMapper;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private JwtProperties jwt;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private InterfaceWhiteMapper interfaceWhiteMapper;

    @Override
    public List<User> getUserList() {
        return userMapper.selectAll();
    }

    @Override
    public User findByUserNameAndPassWord(String userName, String passWord) {
        User u = userMapper.findByUserNameAndPassWord(userName, passWord);
        String id = u.getId();
        List<String> roleIds = relUserRoleMapper.findRoleIdByUserId(id);
        u.setRoleId(roleIds);
        return u;
    }

    @Override
    public Boolean hasPassPermission(String url, String token) {
        //获取接口白名单
        List<InterfaceWhite> interfaceWhites = null;
        if (redisUtils.hasKey(RedisTag.INTERFACE_WHITE)) {
            interfaceWhites =  (List<InterfaceWhite>) redisUtils.get(RedisTag.INTERFACE_WHITE);
        }else {
            interfaceWhites = interfaceWhiteMapper.selectAll();
            redisUtils.set(RedisTag.INTERFACE_WHITE,interfaceWhites);
        }
        if (interfaceWhites != null && interfaceWhites.size() > 0) {
            for (InterfaceWhite interfaceWhite : interfaceWhites) {
                String interfaceUrl = interfaceWhite.getInterfaceUrl();
                if (interfaceUrl.contains("**")) {
                    interfaceUrl = interfaceUrl.replace("/**", "");
                }
                if (url.equals(interfaceUrl)) {
                    return true;
                }
            }
        }

        if (StringUtils.isNotBlank(token) && redisUtils.hasKey(token) && redisUtils.getExpire(token) >= 0) {
            //token不为空,判断redis中token对应的value是否存在,判断redis中的用户数据是否过期
            //刷新token
            redisUtils.expire(token, 60L * 30);
            //获取当前登陆用户的角色
            UserInfo infoFromToken = null;
            try {
                infoFromToken = JwtUtils.getInfoFromToken(token, jwt.getPublicKey());
            } catch (Exception e) {
                return false;
            }
            List<String> roleIds = infoFromToken.getRoleId();
            //获取角色对应接口权限
            Map<String, List<String>> vo;
            if (redisUtils.hasKey(RedisTag.ROLE_PERMISSION)) {
                vo = (Map<String, List<String>>) redisUtils.get(RedisTag.ROLE_PERMISSION);
            }else{
                List<RolePermissionListVo> rolePermissionListVo = roleMapper.findRolePermissionListVo();
                vo = rolePermissionListVo
                        .stream()
                        .filter(Objects::nonNull)
                        .collect(Collectors
                                .groupingBy(RolePermissionListVo::getRoleId))
                        .entrySet()
                        .stream()
                        .map(v1 -> {
                            Map<String, List<String>> map = new HashMap<>();
                            String key = v1.getKey();
                            List<String> collect1 = v1.getValue().stream().filter(Objects::nonNull).map(v2 -> {
                                return v2.getInterfaceUrl();
                            }).collect(Collectors.toList());
                            map.put(key, collect1);
                            return map;
                        }).findFirst().get();
                //TODO 结果
//                System.out.println(vo);
                redisUtils.set(RedisTag.ROLE_PERMISSION,vo);
            }

            for (String roleId : roleIds) {
                List<String> urls = vo.get(roleId);
                if (urls.contains(url)) {
                    return true;
                }
            }
        }
        return false;
    }
}
