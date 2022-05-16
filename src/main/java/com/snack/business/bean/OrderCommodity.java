package com.snack.business.bean;

import java.math.BigDecimal;

public class OrderCommodity {
    private String oId;

    private Integer cId;

    private String cName;

    private BigDecimal cPrice;

    private Integer cNumber;

    private Commodity commodity;

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId == null ? null : oId.trim();
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName == null ? null : cName.trim();
    }

    public BigDecimal getcPrice() {
        return cPrice;
    }

    public void setcPrice(BigDecimal cPrice) {
        this.cPrice = cPrice;
    }

    public Integer getcNumber() {
        return cNumber;
    }

    public void setcNumber(Integer cNumber) {
        this.cNumber = cNumber;
    }
}