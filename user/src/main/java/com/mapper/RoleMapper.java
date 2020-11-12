package com.mapper;

import com.common.base.MyMapper;
import com.entity.Role;
import com.vo.RolePermissionListVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description: 角色Mapper
 * @author: yinminxin
 * @create: 2020-11-06
 **/
@Repository
public interface RoleMapper extends MyMapper<Role, Long> {

    @Select("SELECT r.id roleId, p.interface_url interfaceUrl FROM role r\n" +
            "LEFT JOIN rel_role_permission rrp ON rrp.role_id = r.id\n" +
            "LEFT JOIN permission p ON rrp.permission_id = p.id\n" +
            "WHERE rrp.state = 0 AND p.state = 0 AND r.state = 0")
    List<RolePermissionListVo> findRolePermissionListVo();
}
