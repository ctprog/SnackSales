package com.snack.business.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Order {
    private String oId;

    private Date oStartTime;

    private Date oEndTime;

    private String uId;

    private String oState;

    private Integer mId;

    private Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    private BigDecimal allPrice;

    private List<OrderCommodity> orderCom;

    public List<OrderCommodity> getOrderCom() {
        return orderCom;
    }

    public void setOrderCom(List<OrderCommodity> orderCom) {
        this.orderCom = orderCom;
    }

    public BigDecimal getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(BigDecimal allPrice) {
        this.allPrice = allPrice;
    }
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

    public String getoState() {
        return oState;
    }

    public void setoState(String oState) {
        this.oState = oState == null ? null : oState.trim();
    }

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }
}