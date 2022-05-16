package com.snack.business.controller;

import com.snack.business.bean.Msg;
import com.snack.business.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;

@Controller
public class ImgController {

    @Autowired
    private ImgService imgService;

    @ResponseBody
    @DeleteMapping("/img")
    public Msg deleteImg(String iName, HttpSession session){
        ServletContext servletContext = session.getServletContext();
        String imagePath = servletContext.getRealPath("static") + File.separator + "image";
        imgService.deleteImgByName(imagePath,iName);
        return Msg.success();
    }
}
