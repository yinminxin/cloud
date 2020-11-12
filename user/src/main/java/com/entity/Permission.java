package com.entity;

import com.common.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author yinminxin
 * @description 权限表
 * @date 2020/5/29 16:46
 */
@Entity
@Table(name = "permission")
@org.hibernate.annotations.Table(appliesTo = "permission", comment = "权限表")
public class Permission extends BaseEntity {

    @Column(name = "name", columnDefinition = "VARCHAR(20) NOT NULL COMMENT '权限名称'")
    private String name;
    @Column(name = "interface_url", columnDefinition = "VARCHAR(50) NOT NULL COMMENT '接口路径'")
    private String interfaceUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInterfaceUrl() {
        return interfaceUrl;
    }

    public void setInterfaceUrl(String interfaceUrl) {
        this.interfaceUrl = interfaceUrl;
    }
}
