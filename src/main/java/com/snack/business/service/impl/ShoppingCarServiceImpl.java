package com.snack.business.service.impl;

import com.snack.business.bean.ShoppingCar;
import com.snack.business.bean.ShoppingCarExample;
import com.snack.business.dao.ShoppingCarMapper;
import com.snack.business.service.ShoppingCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCarServiceImpl implements ShoppingCarService {
    @Autowired
    private ShoppingCarMapper shoppingCarMapper;
    @Override
    public void addShoppingCar(String uId) {
        ShoppingCar shoppingCar = new ShoppingCar();
        shoppingCar.setuId(uId);
        shoppingCarMapper.insert(shoppingCar);
    }

    @Override
    public ShoppingCar getCar(String uId) {
        ShoppingCarExample examp = new ShoppingCarExample();
        ShoppingCarExample.Criteria criteria = examp.createCriteria();
        criteria.andUIdEqualTo(uId);
        List<ShoppingCar> shoppingCars = shoppingCarMapper.selectByExample(examp);
        return shoppingCars.get(0);
    }
}
