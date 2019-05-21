package com.gblau.controller.customer;

import com.gb.common.model.po.Appointment;
import com.gb.common.model.po.Order;
import com.gb.common.util.Log;
import com.gblau.service.AppointmentService;
import com.gblau.service.OrderService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    @Autowired
    private OrderService orderService;

    @RequiresAuthentication
    @PostMapping("/addAppointment")
    public ModelAndView addAppointment(ModelAndView modelAndView, Appointment appointment, String viewname) {
        Log.warn("添加预约 {}", appointment);
        appointment.setTime(new Date());
        appointment.setTownId(1);
        appointmentService.insertSelective(appointment);
        modelAndView.addObject("message", "预约成功!");
        modelAndView.setViewName("redirect:shop/"+viewname);
        return modelAndView;
    }

    @GetMapping("/cancelOrder")
    @RequiresAuthentication
    public ModelAndView updateOrder(ModelAndView modelAndView, Order order) {
        Log.warn("更新订单：{}", order);
        orderService.updateByPrimaryKeySelective(order);
        modelAndView.setViewName("redirect:customer/customerCenter");
        return modelAndView;
    }
}
