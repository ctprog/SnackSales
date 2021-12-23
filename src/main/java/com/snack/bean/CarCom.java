package com.snack.bean;

public class CarCom {
    private Integer cId;

    private Integer sId;

    private Integer cCount;

    private Commodity commodity;

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public Integer getcCount() {
        return cCount;
    }

    public void setcCount(Integer cCount) {
        this.cCount = cCount;
    }

    @Override
    public String toString() {
        return "CarCom{" +
                "cId=" + cId +
                ", sId=" + sId +
                ", cCount=" + cCount +
                ", commodity=" + commodity +
                '}';
    }
}