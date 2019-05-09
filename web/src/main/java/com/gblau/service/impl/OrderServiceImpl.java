package com.gblau.service.impl;

import com.gb.common.model.po.Order;
import com.gblau.engine.mapper.OrderMapper;
import com.gblau.service.OrderService;
import com.gblau.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gblau
 * @date 2019-05-07
 */
@Service
public class OrderServiceImpl  extends BaseServiceImpl<Order> implements OrderService {
    @Autowired
    private OrderMapper dao;

    @Autowired
    @Override
    public void setBaseMapper() {
        super.setDataAccessObject(dao);
    }
}
