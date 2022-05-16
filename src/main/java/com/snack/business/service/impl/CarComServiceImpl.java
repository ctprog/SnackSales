package com.snack.business.service.impl;

import com.snack.business.bean.CarCom;
import com.snack.business.bean.CarComExample;
import com.snack.business.dao.CarComMapper;
import com.snack.business.service.CarComService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarComServiceImpl implements CarComService {

    @Autowired
    private CarComMapper carComMapper;

    @Override
    public void saveCom(CarCom carCom) {
        CarComExample examp = new CarComExample();
        CarComExample.Criteria criteria = examp.createCriteria();
        criteria.andSIdEqualTo(carCom.getsId());
        List<CarCom> carComs = carComMapper.selectByExample(examp);
        Boolean has = false;
        for (CarCom com : carComs) {
            if (com.getcId().equals(carCom.getcId())){
                carCom.setcCount(com.getcCount()+1);
                has = true;
                break;
            }
        }
        if (has){
            criteria.andCIdEqualTo(carCom.getcId());
            carComMapper.updateByExample(carCom,examp);
        }else {
            carComMapper.insert(carCom);
        }
    }

    @Override
    public List<CarCom> getCarComs(Integer sId) {
        CarComExample examp = new CarComExample();
        CarComExample.Criteria criteria = examp.createCriteria();
        criteria.andSIdEqualTo(sId);
        List<CarCom> carComs = carComMapper.selectByExample(examp);
        return carComs;
    }

    @Override
    public void deleteCom(CarCom carCom) {
        CarComExample examp = new CarComExample();
        CarComExample.Criteria criteria = examp.createCriteria();
        criteria.andSIdEqualTo(carCom.getsId());
        criteria.andCIdEqualTo(carCom.getcId());
        carComMapper.deleteByExample(examp);
    }
}
