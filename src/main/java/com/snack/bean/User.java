package com.snack.bean;

import javax.validation.constraints.Pattern;

public class User {
    @Pattern(regexp = "^[1-9][0-9]{5}$",message = "ID格式不正确！")
    private String uId;

    @Pattern(regexp = "(^[a-zA-Z]{1}[a-zA-Z\\s]{0,20}[a-zA-Z]{1}$)|(^(?:[\\u4e00-\\u9fa5·]{2,16})$)",message = "用户名格式不正确！")
    private String uName;

    @Pattern(regexp = "^[a-zA-Z0-9]{6,12}$",message = "密码格式不正确！")
    private String uPassword;

    private String uGender;

    @Pattern(regexp = "^(?:(?:\\+|00)86)?1\\d{10}$",message = "电话号码格式不正确！")
    private String uPhone;

    private String uHead;

    private Integer mId;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId == null ? null : uId.trim();
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName == null ? null : uName.trim();
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword == null ? null : uPassword.trim();
    }

    public String getuGender() {
        return uGender;
    }

    public void setuGender(String uGender) {
        this.uGender = uGender == null ? null : uGender.trim();
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone == null ? null : uPhone.trim();
    }

    public String getuHead() {
        return uHead;
    }

    public void setuHead(String uHead) {
        this.uHead = uHead == null ? null : uHead.trim();
    }

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }
}