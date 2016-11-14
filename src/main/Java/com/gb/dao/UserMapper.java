package com.gb.dao;

import com.gb.model.User;

import java.util.List;
import java.util.Set;

/**
 * Created by gblau on 2016-11-12.
 */
public interface UserMapper extends Mapper<User> {
    String selectByUsername(String username);
    Set<String> selectRoles(int id);
    Set<String> selectPermissions(int id);
    List<String> selectPermissionUrl(String principal);
}
