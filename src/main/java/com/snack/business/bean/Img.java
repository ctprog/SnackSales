package com.snack.business.bean;

public class Img {
    private Integer cId;

    private String iName;

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getiName() {
        return iName;
    }

    public void setiName(String iName) {
        this.iName = iName == null ? null : iName.trim();
    }
}