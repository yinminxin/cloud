package com.config;

import com.common.constant.RedisTag;
import com.entity.InterfaceWhite;
import com.mapper.InterfaceWhiteMapper;
import com.mapper.RoleMapper;
import com.utils.RedisUtils;
import com.vo.RolePermissionListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yinminxin
 * @description 初始化
 * @date 2020/5/29 16:57
 */
@Component
public class ComponentInit {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private InterfaceWhiteMapper interfaceWhiteMapper;

    /**
     * @Description: 初始化角色权限信息到redis缓存中
     * @Author: yinminxin
     * @Date: 2020/5/29 17:00
     */
    @PostConstruct
    public void initPermissionToRedis(){

        List<InterfaceWhite> interfaceWhites = interfaceWhiteMapper.selectAll();
        redisUtils.set(RedisTag.INTERFACE_WHITE,interfaceWhites);

        List<RolePermissionListVo> rolePermissionListVo = roleMapper.findRolePermissionListVo();
        System.out.println(rolePermissionListVo);
        Map<String, List<String>> roleMap = rolePermissionListVo
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
//        //TODO 结果
//        System.out.println(roleMap);
        redisUtils.set(RedisTag.ROLE_PERMISSION,roleMap);
//        Map<String, List<String>> vo = (Map<String, List<String>>) redisUtils.get(RedisTag.ROLE_PERMISSION);
//        System.out.println(vo);
    }

}
