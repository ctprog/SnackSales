package com.snack.business.controller;

import com.snack.business.bean.*;
import com.snack.constant.SnackConstant;
import com.snack.business.service.CarComService;
import com.snack.business.service.CommodityService;
import com.snack.business.service.ShoppingCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ShoppingCarController {
    @Autowired
    private ShoppingCarService shoppingCarService;

    @Autowired
    private CarComService carComService;

    @Autowired
    private CommodityService commodityService;

    @ResponseBody
    @GetMapping("/shoppingCar/coms")
    public Msg getShoppingCar(HttpSession session){
        User user = (User) session.getAttribute(SnackConstant.LOGIN_USER);
        ShoppingCar shoppingCar = shoppingCarService.getCar(user.getuId());
        List<CarCom> carComs = carComService.getCarComs(shoppingCar.getsId());
        for (CarCom carCom : carComs) {
            Commodity oneCommodity = commodityService.getOneCommodity(carCom.getcId());
            carCom.setCommodity(oneCommodity);
        }
        shoppingCar.setCarComs(carComs);
        return Msg.success().add("shoppingCar",shoppingCar);
    }

    @ResponseBody
    @PostMapping("/shoppingCar/{cId}")
    public Msg saveComToCar(@PathVariable("cId") Integer cId, HttpSession session){
        User user = (User) session.getAttribute(SnackConstant.LOGIN_USER);
        ShoppingCar shoppingCar = shoppingCarService.getCar(user.getuId());
        CarCom carCom = new CarCom();
        carCom.setcId(cId);
        carCom.setcCount(1);
        carCom.setsId(shoppingCar.getsId());
        carComService.saveCom(carCom);
        return Msg.success();
    }

    @ResponseBody
    @DeleteMapping("/shoppingCar/{cId}")
    public Msg deleteCarCom(@PathVariable("cId")Integer cId,HttpSession session){
        User user = (User) session.getAttribute(SnackConstant.LOGIN_USER);
        ShoppingCar shoppingCar = shoppingCarService.getCar(user.getuId());
        CarCom carCom = new CarCom();
        carCom.setcId(cId);
        carCom.setcCount(1);
        carCom.setsId(shoppingCar.getsId());
        carComService.deleteCom(carCom);
        return Msg.success();
    }
}
