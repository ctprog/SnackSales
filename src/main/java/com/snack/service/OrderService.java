package com.snack.service;

import com.snack.bean.Order;
import com.snack.bean.ShoppingCar;
import com.snack.bean.User;

import java.util.List;
import java.util.Map;

public interface OrderService {

    void saveOrder(List<Map<String, String>> carComs, ShoppingCar shoppingCar,Integer mId);

    List<Order> getOrders(String uId);

    void updateOrders(String oId);

    List<Order> getAllOrders();
}
