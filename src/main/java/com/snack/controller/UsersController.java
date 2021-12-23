package com.snack.controller;

import com.snack.bean.Msg;
import com.snack.bean.User;
import com.snack.service.ImgService;
import com.snack.service.ShoppingCarService;
import com.snack.service.UsersService;
import com.snack.utils.ImgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;

@Controller
public class UsersController {

    @Autowired
    private ShoppingCarService shoppingCarService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private ImgService imgService;

    @ResponseBody
    @GetMapping("/user/isLogin")
    public Msg isLogin(HttpSession session){
        if (session.getAttribute("user")==null){
            return Msg.fail();
        }
        return Msg.success();
    }

    @ResponseBody
    @GetMapping("/user")
    public Msg getUserMessage(HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user==null){
            return Msg.fail();
        }else {
            return Msg.success().add("user",user);
        }
    }

    @ResponseBody
    @PostMapping("/user/login")
    public Msg login(User user ,HttpSession session){
        if (usersService.userLogin(user)){
            User user1 = usersService.getUserById(user.getuId());
            session.setAttribute("user",user1);
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }

    @ResponseBody
    @GetMapping("/user/quit")
    public Msg quit(HttpSession session){
        session.removeAttribute("user");
        return Msg.success();
    }

    @ResponseBody
    @PostMapping("/user/register")
    public Msg register(@Valid User user, BindingResult result, MultipartFile img,HttpSession session){
        if (result.hasErrors()){
            return Msg.fail().add("merror","注册信息填写格式不正确");
        }else if (img.getSize()==0){
            return Msg.fail().add("error","头像不能为空！");
        }if (!ImgUtils.imgValid(img.getOriginalFilename())){
            return Msg.fail().add("error","头像图片格式不正确");
        }
        ServletContext servletContext = session.getServletContext();
        String imagePath = servletContext.getRealPath("static") + File.separator + "image";
        User user1 = imgService.addUserHead(user, img, imagePath);
        usersService.saveUser(user1);
        shoppingCarService.addShoppingCar(user.getuId());
        return Msg.success();
    }

    @ResponseBody
    @PutMapping("/user/update")
    public Msg updateUser(User user,HttpSession session){
        User user1 = (User) session.getAttribute("user");
        user.setuId(user1.getuId());
        user.setuHead(user1.getuHead());
        user.setmId(user1.getmId());
        usersService.updateUser(user);
        session.removeAttribute("user");
        return Msg.success();
    }

    @ResponseBody
    @PutMapping("/user/update/{mId}")
    public Msg updateUserMId(@PathVariable("mId") Integer mId, HttpSession session){
        User user = (User) session.getAttribute("user");
        user.setmId(mId);
        usersService.updateUser(user);
        return Msg.success();
    }
}
