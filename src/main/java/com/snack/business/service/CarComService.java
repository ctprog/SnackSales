package com.snack.business.service;

import com.snack.business.bean.CarCom;

import java.util.List;

public interface CarComService {

    void saveCom(CarCom carCom);

    List<CarCom> getCarComs(Integer getsId);

    void deleteCom(CarCom carCom);
}
