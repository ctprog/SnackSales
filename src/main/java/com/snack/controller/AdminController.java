package com.snack.controller;

import com.snack.bean.Admin;
import com.snack.bean.Msg;
import com.snack.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/adminCheck")
    @ResponseBody
    public Msg adminCheck(Admin admin, HttpServletRequest request){
        if (adminService.adminLogin(admin)){
            request.getSession().setAttribute("aId",admin.getaId());
            return Msg.success();
        } else {
            return Msg.fail().add("error","账号或密码错误！");
        }
    }
    @GetMapping("/adminLogin")
    public String adminLogin(HttpServletRequest request){
        if (request.getSession().getAttribute("aId")!=null){
            return "admin";
        } else {
            return "adminLogin";
        }
    }
}
