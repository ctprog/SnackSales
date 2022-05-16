package com.snack.business.controller;

import com.snack.business.bean.Admin;
import com.snack.business.bean.Msg;
import com.snack.constant.SnackConstant;
import com.snack.business.service.AdminService;
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
            request.getSession().setAttribute(SnackConstant.LOGIN_ADMIN,admin);
            return Msg.success();
        } else {
            return Msg.fail().add("error", SnackConstant.MESSAGE_LOGIN_FAILED);
        }
    }
    @GetMapping("/adminLogin")
    public String adminLogin(HttpServletRequest request){
        if (request.getSession().getAttribute(SnackConstant.LOGIN_ADMIN)!=null){
            return "admin";
        } else {
            return "adminLogin";
        }
    }
    @GetMapping("/admin/quit")
    public String adminQuit(HttpServletRequest request){
        request.getSession().removeAttribute(SnackConstant.LOGIN_ADMIN);
        return "register";
    }
}
