package com.snack.service.impl;

import com.snack.bean.Message;
import com.snack.bean.MessageExample;
import com.snack.dao.MessageMapper;
import com.snack.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public List<Message> getAllMessage(String uId) {
        MessageExample examp = new MessageExample();
        MessageExample.Criteria criteria = examp.createCriteria();
        criteria.andUIdEqualTo(uId);
        List<Message> messages = messageMapper.selectByExample(examp);
        return messages;
    }

    @Override
    public void saveMessage(Message message) {
        messageMapper.insert(message);
    }

    @Override
    public void updateMessage(Message message) {
        messageMapper.updateByPrimaryKey(message);
    }

    @Override
    public void deleteMessage(Integer mId) {
        messageMapper.deleteByPrimaryKey(mId);
    }

    @Override
    public Message getOneMessage(Integer mId) {
        return messageMapper.selectByPrimaryKey(mId);
    }
}
