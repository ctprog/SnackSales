package com.snack.business.service;

import com.snack.business.bean.Message;

import java.util.List;

public interface MessageService {
    List<Message> getAllMessage(String uId);

    void saveMessage(Message message);

    void updateMessage(Message message);

    void deleteMessage(Integer mId);

    Message getOneMessage(Integer mId);
}
