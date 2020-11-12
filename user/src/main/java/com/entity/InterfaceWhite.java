package com.entity;

import com.common.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @description: 接口白名单
 * @author: yinminxin
 * @create: 2020-11-06
 **/
@Entity
@Table(name = "interface_white")
@org.hibernate.annotations.Table(appliesTo = "interface_white", comment = "接口白名单表")
public class InterfaceWhite extends BaseEntity {

    @Column(name = "interface_url", columnDefinition = "VARCHAR(50) NOT NULL COMMENT '接口路径'")
    private String interfaceUrl;

    public String getInterfaceUrl() {
        return interfaceUrl;
    }

    public void setInterfaceUrl(String interfaceUrl) {
        this.interfaceUrl = interfaceUrl;
    }
}
