package com.entity;

import com.common.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author yinminxin
 * @description 用户角色表
 * @date 2020/5/29 16:48
 */
@Entity
@Table(name = "rel_user_role")
@org.hibernate.annotations.Table(appliesTo = "rel_user_role", comment = "用户角色表")
public class RelUserRole extends BaseEntity {

    @Column(name = "user_id", columnDefinition = "VARCHAR(20) NOT NULL COMMENT '用户ID'")
    private String userId;

    @Column(name = "role_id", columnDefinition = "VARCHAR(20) NOT NULL COMMENT '角色ID'")
    private String roleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
