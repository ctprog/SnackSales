package com.snack.business.service.impl;

import com.snack.business.bean.User;
import com.snack.business.bean.UserExample;
import com.snack.constant.SnackConstant;
import com.snack.business.dao.UserMapper;
import com.snack.business.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean userLogin(User user) {
        User user1 = userMapper.selectByPrimaryKey(user.getuId());
        if (user1 == null){
            return false;
        }else if (SnackConstant.USER_IS_DELETE.equals(user1.getIsDelete())){
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
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUIdEqualTo(user.getuId());
        userMapper.updateByExampleSelective(user, example);
    }

    @Override
    public void deleteUser(String uId) {
        User user = userMapper.selectByPrimaryKey(uId);
        user.setIsDelete(SnackConstant.USER_IS_DELETE);
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();
        Stream<User> userStream = userMapper.selectByExample(null).stream().filter(user -> !SnackConstant.USER_IS_DELETE.equals(user.getIsDelete()));
        userStream.forEach(user -> users.add(user));
        return users;
    }
}
