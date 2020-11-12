package com.mapper;

import com.common.base.MyMapper;
import com.entity.RelUserRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description: 用户角色关联表
 * @author: yinminxin
 * @create: 2020-11-06
 **/
@Repository
public interface RelUserRoleMapper extends MyMapper<RelUserRole, Long> {

    @Select("SELECT role_id FROM rel_user_role WHERE state = 0 AND user_id = #{id}")
    List<String> findRoleIdByUserId(@Param("id") String id);
}
