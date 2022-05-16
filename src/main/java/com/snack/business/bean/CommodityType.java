package com.snack.business.bean;

import javax.validation.constraints.Pattern;

public class CommodityType {
    private Integer tId;

    @Pattern(regexp = "^[a-zA-Z\\u4e00-\\u9fa5]{2,15}$")
    private String tName;

    private long cNum;

    public long getcNum() {
        return cNum;
    }

    public void setcNum(long cNum) {
        this.cNum = cNum;
    }

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName == null ? null : tName.trim();
    }
}