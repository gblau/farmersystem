package com.gblau.controller.farmer;

import com.gb.common.model.po.*;
import com.gb.common.util.Log;
import com.gblau.service.*;
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
@RequestMapping("/farmer")
public class FarmerController {
    @Autowired
    private TownService townService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private GoodService goodService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private UserService userService;

    @RequiresRoles("farmer")
    @GetMapping("/home")
    public ModelAndView farmerHome(ModelAndView modelAndView){
        modelAndView.setViewName("farmerHome");
        return modelAndView;
    }

    @RequiresRoles("farmer")
    @GetMapping("/farmerCenter")
    public ModelAndView farmerCenter(ModelAndView modelAndView){
        List<Town> towns = townService.findAllElements();
        List<Store> stores = storeService.findAllElements();
        List<Good> goods = goodService.findAllElements();
        modelAndView.setViewName("farmerCenter");
        modelAndView.addObject("towns", towns);
        modelAndView.addObject("stores", stores);
        modelAndView.addObject("goods", goods);
        return modelAndView;
    }

    private void buildOrder(ModelAndView modelAndView) {
        List<Order> orders = orderService.findAllElements();
        List<Map<String, Object>> notAcceptedOrder = new ArrayList<>(10);
        List<Map<String, Object>> acceptedOrder = new ArrayList<>(10);

        for (Order order : orders) {
            Log.warn("订单：{}", order);
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

            if (new Byte("0").equals(order.isAccepted()))
                notAcceptedOrder.add(map);
            else
                acceptedOrder.add(map);
        }
        Log.warn("订单数量：{}", notAcceptedOrder.size());
        modelAndView.addObject("notAcceptedOrders", notAcceptedOrder);
        modelAndView.addObject("acceptedOrders", acceptedOrder);
    }

    private void buildAppointment(ModelAndView modelAndView) {
        List<Appointment> appointments = appointmentService.findAllElements();
        List<Map<String, Object>> notAccepted = new ArrayList<>(10);
        List<Map<String, Object>> accepted = new ArrayList<>(10);

        for (Appointment appointment : appointments) {
            Log.warn("订单：{}", appointment);
            Map<String, Object> map = new HashMap<>();
            map.put("id", appointment.getId());
            map.put("time", appointment.getTime());
            map.put("arrival", appointment.getArrivalTime());
            map.put("person", appointment.getAppPerson());

            User user = userService.findByPrimaryKey(appointment.getUserId());
            if (user != null) {
                map.put("customerName", user.getUsername());
                map.put("customerPhone", user.getPhone());
                map.put("customerAddress", user.getAddress());
            }

            Town town = townService.findByPrimaryKey(appointment.getTownId());
            if (town != null)
                map.put("image", town.getImage());

            if (new Byte("0").equals(appointment.isAccepted()))
                notAccepted.add(map);
            else
                accepted.add(map);
        }
        Log.warn("订单数量：{}", notAccepted.size());
        modelAndView.addObject("notAcceptedAppointments", notAccepted);
        modelAndView.addObject("acceptedAppointments", accepted);
    }

    @RequiresRoles("farmer")
    @GetMapping("/transaction")
    public ModelAndView transaction(ModelAndView modelAndView){
        buildOrder(modelAndView);
        buildAppointment(modelAndView);
        modelAndView.setViewName("transaction");
        return modelAndView;
    }

    @RequiresRoles("farmer")
    @GetMapping("/searchF")
    public ModelAndView search(ModelAndView modelAndView){
        modelAndView.setViewName("searchF");
        return modelAndView;
    }

    @RequiresRoles("farmer")
    @GetMapping("/helpF")
    public ModelAndView help(ModelAndView modelAndView){
        modelAndView.setViewName("helpF");
        return modelAndView;
    }

}
