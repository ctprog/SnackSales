package com.snack.service;

import com.snack.bean.ShoppingCar;

public interface ShoppingCarService {
    public void addShoppingCar(String uId);

    ShoppingCar getCar(String uId);
}
