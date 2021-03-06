package com.snack.business.controller;

import com.snack.business.bean.Msg;
import com.snack.business.bean.Order;
import com.snack.business.bean.ShoppingCar;
import com.snack.business.bean.User;
import com.snack.constant.SnackConstant;
import com.snack.business.service.CarComService;
import com.snack.business.service.OrderService;
import com.snack.business.service.ShoppingCarService;
import com.snack.business.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {

    @Autowired
    private ShoppingCarService shoppingCarService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private OrderService orderService;

    @ResponseBody
    @PostMapping("/order/submit")
    public Msg subOrder(@RequestBody List<Map<String,String>> carComs, HttpSession session){
        User user = (User) session.getAttribute(SnackConstant.LOGIN_USER);
        User user1 = usersService.getUserById(user.getuId());
        if (user1.getmId()==null){
            return Msg.fail();
        }
        ShoppingCar shoppingCar = shoppingCarService.getCar(user.getuId());
        orderService.saveOrder(carComs,shoppingCar,user1.getmId());
        return Msg.success();
    }

    @ResponseBody
    @GetMapping("/order/select")
    public Msg getOrders(HttpSession session){
        User user = (User) session.getAttribute(SnackConstant.LOGIN_USER);
        List<Order> orders = orderService.getOrders(user.getuId());
        return Msg.success().add("orders",orders);
    }

    @ResponseBody
    @GetMapping("/order/allOrder")
    public Msg getAllOrders(){
        List<Order> orders = orderService.getAllOrders();
        return Msg.success().add("orders",orders);
    }

    @ResponseBody
    @PutMapping("/order/deliver/{oId}")
    public Msg deliverOrders(@PathVariable("oId") String oId){
        orderService.updateOrders(oId);
        return Msg.success();
    }

}
