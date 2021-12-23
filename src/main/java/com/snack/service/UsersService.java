package com.snack.service;

import com.snack.bean.User;

public interface UsersService {
    public boolean userLogin(User user);

    public User getUserById(String getuId);

    public void saveUser(User user);

    void updateUser(User user);
}
