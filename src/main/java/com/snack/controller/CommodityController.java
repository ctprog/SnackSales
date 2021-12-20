package com.snack.controller;

import com.snack.bean.Commodity;
import com.snack.bean.Msg;
import com.snack.service.CommodityService;
import com.snack.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CommodityController {
    @Autowired
    private CommodityService commodityService;

    @Autowired
    private ImgService imgService;

    @ResponseBody
    @PostMapping("/commodity")
    public Msg addCommodity(@Valid Commodity commodity, BindingResult result, @RequestParam("cImg") MultipartFile[] imgs, HttpSession session) throws IOException {
        if (imgs[0].getSize()==0||result.hasErrors()){
            Map<String,String> map = new HashMap<>();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                map.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            map.put("imgs","请上传至少一张图片！");
            return Msg.fail().add("error",map);
        }else {
            Commodity commodityById = commodityService.addCommodity(commodity);
            ServletContext servletContext = session.getServletContext();
            String imagePath = servletContext.getRealPath("static") + File.separator + "image";
            imgService.addImgs(commodityById,imgs,imagePath);
            return Msg.success();
        }
    }

    @ResponseBody
    @GetMapping("/commodity")
    public Msg getAllCommodity(){
        List<Commodity> commoditys = commodityService.getAllCommodity();
        return Msg.success().add("commoditys",commoditys);
    }

    @ResponseBody
    @GetMapping("/commodity/{tId}")
    public Msg getSomeCommodity(@PathVariable("tId") Integer tId){
        List<Commodity> commoditys = commodityService.getSomeCommodity(tId);
        return Msg.success().add("commoditys",commoditys);
    }

    @ResponseBody
    @GetMapping("/commodityOne/{cId}")
    public Msg getOneCommodity(@PathVariable("cId") Integer cId){
        Commodity commodity = commodityService.getOneCommodity(cId);
        return Msg.success().add("commodity",commodity);
    }

    @ResponseBody
    @GetMapping("/commoditylike/{cName}")
    public Msg getCommodityLike(@PathVariable("cName") String cName){
        List<Commodity> commoditys = commodityService.getCommodityLike(cName);
        return Msg.success().add("commoditys",commoditys);
    }

    @ResponseBody
    @DeleteMapping("/commodity/{cIds}")
    public Msg deleteCommodity(@PathVariable("cIds") String cIds,HttpSession session){
        ServletContext servletContext = session.getServletContext();
        String imagePath = servletContext.getRealPath("static") + File.separator + "image";
        List<Integer> delIds = new ArrayList<Integer>();
        String[] strIds = cIds.split("-");
        for (String strId : strIds) {
            delIds.add(Integer.parseInt(strId));
        }
        imgService.deleteImgs(delIds,imagePath);
        commodityService.deleteCommodity(delIds);
        return Msg.success();
    }

    @ResponseBody
    @PostMapping("/commodity/{cId}")
    public Msg updateCommodity(Commodity commodity, BindingResult result,@RequestParam("imgs")  MultipartFile[] imgs, HttpSession session){
        ServletContext servletContext = session.getServletContext();
        String imagePath = servletContext.getRealPath("static") + File.separator + "image";
        commodityService.updateCommodityById(commodity);
        if (imgs[0].getSize()!=0){
            imgService.addImgs(commodity,imgs,imagePath);
        }
        return Msg.success();
    }
}