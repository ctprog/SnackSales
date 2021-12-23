package com.snack.service.impl;

import com.snack.bean.User;
import com.snack.dao.UserMapper;
import com.snack.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean userLogin(User user) {
        User user1 = userMapper.selectByPrimaryKey(user.getuId());
        if (user1 == null){
            return false;
        }else if (user1.getuPassword().equals(user.getuPassword())){
            return true;
        }
        return false;
    }

    @Override
    public User getUserById(String uId) {
        return userMapper.selectByPrimaryKey(uId);
    }

    @Override
    public void saveUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateByPrimaryKey(user);
    }
}
