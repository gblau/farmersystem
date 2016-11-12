package com.gb.service;

import com.gb.dao.UserDataAccessObject;
import com.gb.model.User;
import com.gb.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by gblau on 2016-11-11.
 */
@Service
public class BlogUserService extends BlogService<User> implements UserService {
    private UserDataAccessObject dao;

    @Autowired
    public void setBaseMapper() {
        super.setDataAccessObject(dao);
    }

    @Override
    public User findByUsername(String username) {
        return dao.selectByUsername(username);
    }

    public Set<String> findRoles(int id) {
        return dao.selectRoles(id);
    }

    public Set<String> findPermissions(int id) {
        return dao.selectPermissions(id);
    }
}
