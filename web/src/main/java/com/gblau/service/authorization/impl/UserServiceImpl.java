package com.gblau.service.authorization.impl;

import com.gb.common.model.po.User;
import com.gb.common.util.SqlSearchUtil;
import com.gblau.service.authorization.UserService;
import com.gblau.service.base.impl.BaseServiceImpl;
import com.gblau.engine.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Set<String> findRoles(String username) {
        return dao.selectRoles(username);
    }

    public Set<String> findPermissions(String username) {
        return dao.selectPermissions(username);
    }

    @Override
    public List<User> findAllUsers() {
        return dao.selectAll();
    }

    @Override
    public List<User> findUsersByUsername(String input) {
        return dao.selectUsersByUsername(SqlSearchUtil.getFuzzyQueryString(input));
    }
}
