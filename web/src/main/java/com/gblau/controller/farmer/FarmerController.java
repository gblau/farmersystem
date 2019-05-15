package com.gblau.controller.farmer;

import com.gb.common.model.po.Town;
import com.gblau.service.TownService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author gblau
 * @date 2019-05-13
 */
@RestController
@RequestMapping("/farmer")
public class FarmerController {
    @Autowired
    public TownService townService;

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
        modelAndView.setViewName("farmerCenter");
        modelAndView.addObject("towns", towns);
        return modelAndView;
    }

    @RequiresRoles("farmer")
    @GetMapping("/transaction")
    public ModelAndView transaction(ModelAndView modelAndView){
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
