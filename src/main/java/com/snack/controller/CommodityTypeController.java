package com.snack.controller;

import com.snack.bean.Commodity;
import com.snack.bean.CommodityType;
import com.snack.bean.Msg;
import com.snack.service.CommodityService;
import com.snack.service.CommodityTypeService;
import com.snack.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CommodityTypeController {
    @Autowired
    private CommodityTypeService commodityTypeService;
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private ImgService imgService;

    @ResponseBody
    @GetMapping("/commodityType")
    public Msg getComTypes(){
        List<CommodityType> comTypes = commodityTypeService.getComTypes();
        return Msg.success().add("comTypes",comTypes);
    }

    @ResponseBody
    @PostMapping("/commodityType")
    public Msg saveComType(@Valid CommodityType commodityType,BindingResult result ){
        if (result.hasErrors()){
            return Msg.fail();
        }else {
            commodityTypeService.saveComType(commodityType);
            return Msg.success();
        }
    }
    @ResponseBody
    @PutMapping("/commodityType")
    public Msg updateComType(@Valid CommodityType commodityType,BindingResult result ){
        if (result.hasErrors()){
            return Msg.fail();
        }else {
            commodityTypeService.updateComType(commodityType);
            return Msg.success();
        }
    }
    @ResponseBody
    @DeleteMapping("/commodityType/{tId}")
    public Msg deleteComType(@PathVariable("tId") Integer tId , HttpSession session){
        ServletContext servletContext = session.getServletContext();
        String imagePath = servletContext.getRealPath("static") + File.separator + "image";
        List<Commodity> commodities = commodityService.getSomeCommodity(tId);
        List<Integer> cIds = new ArrayList<>();
        for (Commodity commodity : commodities) {
            cIds.add(commodity.getcId());
        }
        imgService.deleteImgs(cIds,imagePath);
        commodityService.deleteCommodity(cIds);
        commodityTypeService.deleteType(tId);
        return Msg.success();
    }
}
