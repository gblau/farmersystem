package com.gblau.service.authorization.impl;

import com.gb.common.model.po.Permission;
import com.gblau.engine.mapper.PermissionMapper;
import com.gblau.engine.mapper.UserRoleMapper;
import com.gblau.service.authorization.PermissionService;
import com.gblau.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gblau
 * @date 2019-05-07
 */
@Service
public class PermissionServiceImpl  extends BaseServiceImpl<Permission> implements PermissionService {
    @Autowired
    private PermissionMapper dao;

    @Autowired
    @Override
    public void setBaseMapper() {
        super.setDataAccessObject(dao);
    }
}
