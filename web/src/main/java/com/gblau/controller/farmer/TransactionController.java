package com.gblau.controller.farmer;

import com.gb.common.model.po.Appointment;
import com.gb.common.model.po.Order;
import com.gb.common.util.Log;
import com.gblau.controller.base.BaseController;
import com.gblau.service.AppointmentService;
import com.gblau.service.OrderService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author gblau
 * @date 2019-05-16
 */
@RestController
@RequestMapping("/farmer")
public class TransactionController extends BaseController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/updateOrder")
    @RequiresAuthentication
    public ModelAndView updateOrder(ModelAndView modelAndView, Order order) {
        Log.warn("更新订单：{}", order);
        orderService.updateByPrimaryKeySelective(order);
        modelAndView.setViewName("redirect:transaction");
        return modelAndView;
    }

    @GetMapping("/deleteOrder")
    @RequiresRoles("farmer")
    public ModelAndView deleteOrder(ModelAndView modelAndView, Integer orderId) {
        orderService.deleteByPrimaryKey(orderId);
        modelAndView.setViewName("redirect:transaction");
        return modelAndView;
    }

    @GetMapping("/updateAppointment")
    @RequiresRoles("farmer")
    public ModelAndView updateAppointment(ModelAndView modelAndView, Appointment appointment) {
        Log.warn("更新订单：{}", appointment);
        appointmentService.updateByPrimaryKeySelective(appointment);
        modelAndView.setViewName("redirect:transaction");
        return modelAndView;
    }

    @GetMapping("/deleteAppointment")
    @RequiresRoles("farmer")
    public ModelAndView deleteAppointment(ModelAndView modelAndView, Integer appointmentId) {
        orderService.deleteByPrimaryKey(appointmentId);
        modelAndView.setViewName("redirect:transaction");
        return modelAndView;
    }
}
