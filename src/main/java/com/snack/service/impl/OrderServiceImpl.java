package com.snack.service.impl;

import com.snack.bean.*;
import com.snack.dao.*;
import com.snack.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderCommodityMapper orderCommodityMapper;

    @Autowired
    private CommodityMapper commodityMapper;

    @Autowired
    private CarComMapper carComMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public void saveOrder(List<Map<String, String>> carComs, ShoppingCar shoppingCar ,Integer mId) {
        List<CarCom> carComList = new ArrayList<>();
        List<Integer> cIds = new ArrayList<>();
        Order order = new Order();
        int oId= UUID.randomUUID().toString().hashCode();
        if (oId<0){
            oId = -oId;
        }
        String orderId = 1+String.format("%015d",oId);
        order.setuId(shoppingCar.getuId());
        order.setoId(orderId);
        order.setmId(mId);
        for (Map<String, String> carCom : carComs) {
            CarCom com = new CarCom();
            com.setcId(Integer.parseInt(carCom.get("cId")));
            com.setcCount(Integer.parseInt(carCom.get("cCount")));
            carComList.add(com);
        }
        Date startTime = new Date();
        order.setoStartTime(startTime);
        order.setoState("未处理");
        orderMapper.insert(order);
        for (CarCom carCom : carComList) {
            OrderCommodity orderCommodity = new OrderCommodity();
            Commodity commodity = commodityMapper.selectByPrimaryKey(carCom.getcId());
            orderCommodity.setcId(carCom.getcId());
            orderCommodity.setcNumber(carCom.getcCount());
            orderCommodity.setcName(commodity.getcName());
            orderCommodity.setoId(orderId);
            orderCommodity.setcPrice(commodity.getcPrice());
            orderCommodityMapper.insert(orderCommodity);
            cIds.add(carCom.getcId());
        }

        CarComExample ex = new CarComExample();
        CarComExample.Criteria criteria = ex.createCriteria();
        criteria.andCIdIn(cIds);
        carComMapper.deleteByExample(ex);
    }

    @Override
    public List<Order> getOrders(String uId) {
        OrderExample ex = new OrderExample();
        OrderExample.Criteria criteria = ex.createCriteria();
        criteria.andUIdEqualTo(uId);
        List<Order> orders = orderMapper.selectByExample(ex);
        for (Order order : orders) {
            String oId = order.getoId();
            OrderCommodityExample examp = new OrderCommodityExample();
            OrderCommodityExample.Criteria criteria1 = examp.createCriteria();
            criteria1.andOIdEqualTo(oId);
            List<OrderCommodity> orderCommodities = orderCommodityMapper.selectByExample(examp);
            order.setOrderCom(orderCommodities);
            BigDecimal allPrice = new BigDecimal(0);
            for (OrderCommodity orderCommodity : orderCommodities) {
                allPrice = allPrice.add(orderCommodity.getcPrice().multiply(new BigDecimal(orderCommodity.getcNumber())));
                Commodity commodity = commodityMapper.selectByPrimaryKey(orderCommodity.getcId());
                orderCommodity.setCommodity(commodity);
            }
            order.setAllPrice(allPrice);
            Message message = messageMapper.selectByPrimaryKey(order.getmId());
            order.setMessage(message);
        }
        return orders;
    }

    @Override
    public void updateOrders(String oId) {
        Order order = orderMapper.selectByPrimaryKey(oId);
        order.setoState("已发货");
        orderMapper.updateByPrimaryKey(order);
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = orderMapper.selectByExample(null);
        for (Order order : orders) {
            String oId = order.getoId();
            OrderCommodityExample examp = new OrderCommodityExample();
            OrderCommodityExample.Criteria criteria1 = examp.createCriteria();
            criteria1.andOIdEqualTo(oId);
            List<OrderCommodity> orderCommodities = orderCommodityMapper.selectByExample(examp);
            order.setOrderCom(orderCommodities);
            BigDecimal allPrice = new BigDecimal(0);
            for (OrderCommodity orderCommodity : orderCommodities) {
                allPrice = allPrice.add(orderCommodity.getcPrice().multiply(new BigDecimal(orderCommodity.getcNumber())));
                Commodity commodity = commodityMapper.selectByPrimaryKey(orderCommodity.getcId());
                orderCommodity.setCommodity(commodity);
            }
            order.setAllPrice(allPrice);
            Message message = messageMapper.selectByPrimaryKey(order.getmId());
            order.setMessage(message);
        }
        return orders;
    }
}
