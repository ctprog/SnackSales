package com.snack.business.controller;

import com.snack.business.bean.Msg;
import com.snack.business.bean.User;
import com.snack.constant.SnackConstant;
import com.snack.business.service.ImgService;
import com.snack.business.service.ShoppingCarService;
import com.snack.business.service.UsersService;
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
import java.util.List;

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
        if (session.getAttribute(SnackConstant.LOGIN_USER)==null){
            return Msg.fail();
        }
        return Msg.success();
    }

    @ResponseBody
    @GetMapping("/user")
    public Msg getUserMessage(HttpSession session){
        User user = (User) session.getAttribute(SnackConstant.LOGIN_USER);
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
            session.setAttribute(SnackConstant.LOGIN_USER,user1);
            return Msg.success();
        }else {
            return Msg.fail().add("error",SnackConstant.MESSAGE_LOGIN_FAILED);
        }
    }

    @ResponseBody
    @GetMapping("/user/quit")
    public Msg quit(HttpSession session){
        session.removeAttribute(SnackConstant.LOGIN_USER);
        return Msg.success();
    }

    @ResponseBody
    @PostMapping("/user/register")
    public Msg register(@Valid User user, BindingResult result, MultipartFile img,HttpSession session){
        if (result.hasErrors()){
            return Msg.fail().add("error", SnackConstant.MESSAGE_REGISTER_FAILED);
        }else if (img.getSize()==0){
            return Msg.fail().add("error",SnackConstant.MESSAGE_AVATAR_NULL);
        }if (!ImgUtils.imgValid(img.getOriginalFilename())){
            return Msg.fail().add("error",SnackConstant.MESSAGE_AVATAR_FAILED);
        }if (usersService.getUserById(user.getuId())!=null){
            return Msg.fail().add("error",SnackConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
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
        User user1 = (User) session.getAttribute(SnackConstant.LOGIN_USER);
        user.setuId(user1.getuId());
        usersService.updateUser(user);
        User userById = usersService.getUserById(user1.getuId());
        session.setAttribute(SnackConstant.LOGIN_USER,userById);
        return Msg.success();
    }

    @ResponseBody
    @PutMapping("/user/update/{mId}")
    public Msg updateUserMId(@PathVariable("mId") Integer mId, HttpSession session){
        User user = (User) session.getAttribute(SnackConstant.LOGIN_USER);
        user.setmId(mId);
        usersService.updateUser(user);
        return Msg.success();
    }

    @ResponseBody
    @GetMapping("/admin/users")
    public Msg allUser(){
        List<User> users = usersService.getAllUser();
        return Msg.success().add("users",users);
    }

    @ResponseBody
    @DeleteMapping("/admin/delUser/{uId}")
    public Msg deleteUser(@PathVariable String uId){
        usersService.deleteUser(uId);
        return Msg.success();
    }

    @ResponseBody
    @PutMapping("/admin/user")
    public Msg adminUpdateUser(User user){
        User userById = usersService.getUserById(user.getuId());
        userById.setuName(user.getuName());
        userById.setuGender(user.getuGender());
        userById.setuPhone(user.getuPhone());
        userById.setuPassword(user.getuPassword());
        usersService.updateUser(userById);
        return Msg.success();
    }
}
