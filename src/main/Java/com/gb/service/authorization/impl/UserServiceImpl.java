package com.gb.service.authorization.impl;

import com.gb.dao.authorization.UserMapper;
import com.gb.model.po.User;
import com.gb.service.base.impl.BaseServiceImpl;
import com.gb.service.authorization.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by gblau on 2016-11-11.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    @Autowired
    private UserMapper dao;

    @Autowired
    @Override
    public void setBaseMapper() {
        super.setDataAccessObject(dao);
    }

    @Override
    public User findUserByUsername(String username) {
        return dao.selectUserByUsername(username);
    }

    public Set<String> findRoles(String id) {
        return dao.selectRoles(id);
    }

    public Set<String> findPermissions(String id) {
        return dao.selectPermissions(id);
    }
}
