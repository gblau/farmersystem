package com.gb.dao.authorization;

import com.gb.dao.Mapper;
import com.gb.model.po.User;

import java.util.List;
import java.util.Set;

/**
 * Created by gblau on 2016-11-12.
 */
public interface UserMapper extends Mapper<User> {
    User selectUserByUsername(String username);
    Set<String> selectRoles(String id);
    Set<String> selectPermissions(String id);
    List<String> selectPermissionUrl(String principal);
}
