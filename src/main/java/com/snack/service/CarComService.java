package com.snack.service;

import com.snack.bean.CarCom;

import java.util.List;

public interface CarComService {

    void saveCom(CarCom carCom);

    List<CarCom> getCarComs(Integer getsId);

    void deleteCom(CarCom carCom);
}
