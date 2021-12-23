package com.snack.controller;

import com.snack.bean.Message;
import com.snack.bean.Msg;
import com.snack.bean.User;
import com.snack.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.sun.org.apache.xml.internal.serializer.utils.Utils.messages;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @ResponseBody
    @GetMapping("/message/address")
    public Msg getMessage(HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Message> messages = messageService.getAllMessage(user.getuId());
        return Msg.success().add("messages",messages).add("uMId",user.getmId());
    }

    @ResponseBody
    @GetMapping("/message/address/{mId}")
    public Msg getMessage(@PathVariable("mId") Integer mId,HttpSession session){
        Message message = messageService.getOneMessage(mId);
        return Msg.success().add("message",message);
    }

    @ResponseBody
    @PostMapping("/message/address")
    public Msg addMessage(Message message, HttpSession session){
        User user = (User) session.getAttribute("user");
        message.setuId(user.getuId());
        messageService.saveMessage(message);
        return Msg.success();
    }

    @ResponseBody
    @PutMapping("/message/address/{mId}")
    public Msg updateMessage(Message message,@PathVariable("mId") Integer mId, HttpSession session){
        User user = (User) session.getAttribute("user");
        message.setuId(user.getuId());
        message.setmId(mId);
        messageService.updateMessage(message);
        return Msg.success();
    }

    @ResponseBody
    @DeleteMapping("/message/address/{mId}")
    public Msg deleteMessage(@PathVariable("mId") Integer mId){
        messageService.deleteMessage(mId);
        return Msg.success();
    }
}
