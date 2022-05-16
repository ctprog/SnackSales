package com.snack.business.service;

import com.snack.business.bean.User;

import java.util.List;

public interface UsersService {
    public boolean userLogin(User user);

    public User getUserById(String getuId);

    public void saveUser(User user);

    void updateUser(User user);

    void deleteUser(String uId);

    List<User> getAllUser();
}
