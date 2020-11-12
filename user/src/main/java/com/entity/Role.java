package com.entity;

import com.common.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author yinminxin
 * @description 角色表
 * @date 2020/5/29 16:42
 */
@Entity
@Table(name = "role")
@org.hibernate.annotations.Table(appliesTo = "role", comment = "角色表")
public class Role extends BaseEntity {

    @Column(name = "name", columnDefinition = "VARCHAR(20) NOT NULL COMMENT '角色名称'")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
