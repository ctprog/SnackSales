package com.snack.business.service.impl;

import com.snack.business.bean.Admin;
import com.snack.business.dao.AdminMapper;
import com.snack.business.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public boolean adminLogin(Admin admin){
        Admin admin1 = adminMapper.selectByPrimaryKey(admin.getaId());
        if (admin1 == null){
            return false;
        } else if (admin1.getaPassword().equals(admin.getaPassword())){
            return true;
        }
        return false;
    }
}
