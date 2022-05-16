package com.snack.business.service;

import com.snack.business.bean.ShoppingCar;

public interface ShoppingCarService {
    public void addShoppingCar(String uId);

    ShoppingCar getCar(String uId);
}
