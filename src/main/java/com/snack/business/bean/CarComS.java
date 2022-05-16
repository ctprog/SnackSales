package com.snack.business.bean;

import java.util.List;

public class CarComS {
    private List<CarCom> carComs;

    public List<CarCom> getCarComs() {
        return carComs;
    }

    public void setCarComs(List<CarCom> carComs) {
        this.carComs = carComs;
    }

    @Override
    public String toString() {
        return "CarComS{" +
                "carComs=" + carComs +
                '}';
    }
}
