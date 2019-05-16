package com.gblau.controller.customer;

import com.gb.common.model.po.Appointment;
import com.gb.common.model.po.Order;
import com.gb.common.model.po.Town;
import com.gb.common.model.po.User;
import com.gblau.service.AppointmentService;
import com.gblau.service.OrderService;
import com.gblau.service.TownService;
import com.gblau.service.authorization.UserService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gblau
 * @date 2019-05-13
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private UserService userService;
    @Autowired
    private TownService townService;

    @RequiresRoles("customer")
    @GetMapping("/home")
    public ModelAndView customerHome(ModelAndView modelAndView){
        modelAndView.setViewName("customerHome");
        return modelAndView;
    }

    private void buildOrder(ModelAndView modelAndView) {
        List<Order> orders = orderService.findAllElements();
        List<Map<String, Object>> maps = new ArrayList<>(10);

        for (Order order : orders) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", order.getId());
            map.put("name", order.getName());
            map.put("num", order.getNum());

            User user = userService.findByPrimaryKey(order.getUserId());

            if (user != null) {
                map.put("customerName", user.getUsername());
                map.put("customerPhone", user.getPhone());
                map.put("customerAddress", user.getAddress());
            }
            if (order.getIsAccepted() == 0)
                map.put("state", "审核中");
            else
                map.put("state", "已完成");
            maps.add(map);
        }
        modelAndView.addObject("orders", maps);
    }

    private void buildAppointment(ModelAndView modelAndView) {
        List<Appointment> appointments = appointmentService.findAllElements();
        List<Map<String, Object>> maps = new ArrayList<>(10);

        for (Appointment appointment : appointments) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", appointment.getId());
            map.put("time", appointment.getTime());
            map.put("arrival", appointment.getArrivalTime());
            map.put("person", appointment.getAppPerson());

            Town town = townService.findByPrimaryKey(appointment.getTownId());

            if (town != null) {
                map.put("townAddress", town.getAddress());
                map.put("townName", town.getName());
                map.put("townPhone", town.getPhone());
            }
            if (appointment.getIsAccepted() == 0)
                map.put("state", "待访问");
            else
                map.put("state", "已使用");
            maps.add(map);
        }
        modelAndView.addObject("appointments", maps);
    }

    @RequiresRoles("customer")
    @GetMapping("/customerCenter")
    public ModelAndView customerCenter(ModelAndView modelAndView) {
        buildAppointment(modelAndView);
        buildOrder(modelAndView);
        modelAndView.setViewName("customerCenter");
        return modelAndView;
    }

    @RequiresRoles("customer")
    @GetMapping("/searchC")
    public ModelAndView search(ModelAndView modelAndView){
        modelAndView.setViewName("searchC");
        return modelAndView;
    }

    @RequiresRoles("customer")
    @GetMapping("/helpC")
    public ModelAndView help(ModelAndView modelAndView){
        modelAndView.setViewName("helpC");
        return modelAndView;
    }
}
