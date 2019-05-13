package com.gblau.service.authorization;

import com.gb.common.model.po.User;
import com.gblau.service.base.BaseService;

import java.util.List;
import java.util.Set;

/**
 * Created by gblau on 2016-11-12.
 */
public interface UserService extends BaseService<User> {
    User findUserByUsername(String username);
    Set<String> findRoles(Integer id);
    Set<String> findPermissions(Integer id);
    List<User> findAllUsers();
}
