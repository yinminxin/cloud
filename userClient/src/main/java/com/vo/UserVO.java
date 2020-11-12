package com.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.sql.Timestamp;

/**
 * @description: 用户VO
 * @author: yinminxin
 * @create: 2020-11-06
 **/
public class UserVO {

    private String id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private short state;
    private String name;
    private String userName;
    private String passWord;
    private String phone;
    private String backPhone;
    private String cardNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBackPhone() {
        return backPhone;
    }

    public void setBackPhone(String backPhone) {
        this.backPhone = backPhone;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public UserVO(String id, Timestamp createTime, Timestamp updateTime, short state, String name, String userName, String passWord, String phone, String backPhone, String cardNo) {
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.state = state;
        this.name = name;
        this.userName = userName;
        this.passWord = passWord;
        this.phone = phone;
        this.backPhone = backPhone;
        this.cardNo = cardNo;
    }

    public UserVO() {
    }
}
