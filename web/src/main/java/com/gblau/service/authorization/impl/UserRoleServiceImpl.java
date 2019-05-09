package com.gblau.service.authorization.impl;

import com.gb.common.model.po.UserRole;
import com.gblau.engine.mapper.UserRoleMapper;
import com.gblau.service.authorization.UserRoleService;
import com.gblau.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gblau
 * @date 2019-05-01
 */
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole> implements UserRoleService {
    @Autowired
    private UserRoleMapper dao;

    @Autowired
    @Override
    public void setBaseMapper() {
        super.setDataAccessObject(dao);
    }
}
