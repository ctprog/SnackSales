package com.snack.business.service;

import com.snack.business.bean.Order;
import com.snack.business.bean.ShoppingCar;

import java.util.List;
import java.util.Map;

public interface OrderService {

    void saveOrder(List<Map<String, String>> carComs, ShoppingCar shoppingCar,Integer mId);

    List<Order> getOrders(String uId);

    void updateOrders(String oId);

    List<Order> getAllOrders();
}
