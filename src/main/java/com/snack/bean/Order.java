package com.snack.bean;

import java.util.Date;

public class Order {
    private String oId;

    private Date oStartTime;

    private Date oEndTime;

    private String uId;

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId == null ? null : oId.trim();
    }

    public Date getoStartTime() {
        return oStartTime;
    }

    public void setoStartTime(Date oStartTime) {
        this.oStartTime = oStartTime;
    }

    public Date getoEndTime() {
        return oEndTime;
    }

    public void setoEndTime(Date oEndTime) {
        this.oEndTime = oEndTime;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId == null ? null : uId.trim();
    }
}