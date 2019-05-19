package com.gblau.controller.customer;

import com.gb.common.model.po.*;
import com.gblau.service.AppointmentService;
import com.gblau.service.GoodService;
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
    @Autowired
    private GoodService goodService;

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
            Good good = goodService.findByPrimaryKey(order.getGoodId());
            map.put("image", good.getImage());

            if (new Byte("1").equals(order.isAccepted()))
                map.put("state", "已完成");
            else if (new Byte("0").equals(order.isAccepted()))
                map.put("state", "审核中");
            else if (new Byte("2").equals(order.isAccepted()))
                map.put("state", "已回绝");
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
                map.put("image", town.getImage());
            }

            if (new Byte("0").equals(appointment.isAccepted()))
                map.put("state", "待访问");
            else if (new Byte("1").equals(appointment.isAccepted()))
                map.put("state", "已使用");
            else if (new Byte("2").equals(appointment.isAccepted()))
                map.put("state", "已回绝");
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
