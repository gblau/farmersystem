package com.gb.service;

import com.gb.dao.DataAccessObject;
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
    private UserDataAccessObject userDao;

    @Autowired
    @Override
    public void setBaseMapper(DataAccessObject dao) {
        super.setBaseMapper(dao);
    }

    public Set<String> findRoles(int id) {
        return null;
    }

    public Set<String> findPermissions(int id) {
        return null;
    }

    public List<String> findPermissionUrl(String principal) {
        return null;
    }
}
