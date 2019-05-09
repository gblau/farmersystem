package com.gblau.service.impl;

import com.gb.common.model.po.Store;
import com.gblau.engine.mapper.StoreMapper;
import com.gblau.engine.mapper.UserRoleMapper;
import com.gblau.service.StoreService;
import com.gblau.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gblau
 * @date 2019-05-07
 */
@Service
public class StoreServiceImpl  extends BaseServiceImpl<Store> implements StoreService {
    @Autowired
    private StoreMapper dao;

    @Autowired
    @Override
    public void setBaseMapper() {
        super.setDataAccessObject(dao);
    }
}
