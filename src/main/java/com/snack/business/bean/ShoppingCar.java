package com.snack.business.bean;

import java.util.List;

public class ShoppingCar {
    private Integer sId;

    private String uId;

    private List<CarCom> carComs;

    public List<CarCom> getCarComs() {
        return carComs;
    }

    public void setCarComs(List<CarCom> carComs) {
        this.carComs = carComs;
    }

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId == null ? null : uId.trim();
    }
}