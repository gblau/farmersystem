package com.gblau.controller.customer;

import com.gb.common.model.po.Appointment;
import com.gb.common.util.Log;
import com.gblau.service.AppointmentService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * @author gblau
 * @date 2019-05-16
 */
@RestController
@RequestMapping("/")
public class CustomerCentorController {
    @Autowired
    private AppointmentService appointmentService;

    @RequiresAuthentication
    @PostMapping("/addAppointment")
    public ModelAndView addAppointment(ModelAndView modelAndView, Appointment appointment) {
        Log.warn("添加预约 {}", appointment);
        appointment.setTime(new Date());
        appointment.setTownId(1);
        appointmentService.insertSelective(appointment);
        modelAndView.addObject("message", "预约成功!");
        modelAndView.setViewName("shop/amin01F");
        return modelAndView;
    }
}
