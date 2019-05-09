package com.gblau.service.impl;

import com.gb.common.model.po.Appointment;
import com.gb.common.model.po.UserRole;
import com.gblau.engine.mapper.AppointmentMapper;
import com.gblau.service.AppointmentService;
import com.gblau.service.authorization.UserRoleService;
import com.gblau.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gblau
 * @date 2019-05-07
 */
@Service
public class AppointmentServiceImpl  extends BaseServiceImpl<Appointment> implements AppointmentService {
    @Autowired
    private AppointmentMapper dao;

    @Autowired
    @Override
    public void setBaseMapper() {
        super.setDataAccessObject(dao);
    }
}
