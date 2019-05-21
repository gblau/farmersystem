package com.gblau.service.impl;

import com.gb.common.model.po.Town;
import com.gblau.engine.mapper.TownMapper;
import com.gblau.service.TownService;
import com.gblau.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gblau
 * @date 2019-05-07
 */
@Service
public class TownServiceImpl  extends BaseServiceImpl<Town> implements TownService {
    @Autowired
    private TownMapper dao;

    @Autowired
    @Override
    public void setBaseMapper() {
        super.setDataAccessObject(dao);
    }

    @Override
    public List<Town> fingByUserId(Integer userId) {
        return dao.selectByUserId(userId);
    }
}
