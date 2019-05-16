package com.gblau.service.impl;

import com.gb.common.model.po.Good;
import com.gblau.engine.mapper.GoodMapper;
import com.gblau.service.GoodService;
import com.gblau.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gblau
 * @date 2019-05-07
 */
@Service
public class GoodServiceImpl extends BaseServiceImpl<Good> implements GoodService {
    @Autowired
    private GoodMapper dao;

    @Autowired
    @Override
    public void setBaseMapper() {
        super.setDataAccessObject(dao);
    }

    @Override
    public List<Good> findByStore(Integer storeId) {
        return dao.selectByStore(storeId);
    }
}
