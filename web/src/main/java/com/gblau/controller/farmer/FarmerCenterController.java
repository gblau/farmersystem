package com.gblau.controller.farmer;

import com.gb.common.model.po.Good;
import com.gb.common.model.po.Town;
import com.gb.common.model.po.User;
import com.gblau.service.GoodService;
import com.gblau.service.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @author gblau
 * @date 2019-05-13
 */
@RestController
@RequestMapping("/farmer")
public class FarmerCenterController {

    @Autowired
    private TownService townService;
    @Autowired
    GoodService goodService;
    @Autowired
    HttpSession session;

    @PostMapping("/addTown")
    public ModelAndView addTown(ModelAndView modelAndView, Town town) {
        User user = (User) session.getAttribute("currentUser");
        town.setUserId(user.getId());
        townService.insertSelective(town);
        modelAndView.setViewName("redirect:farmerCenter");
        return modelAndView;
    }

    @PostMapping("/deleteTown")
    public ModelAndView deleteTown(ModelAndView modelAndView, Integer townId) {
        townService.deleteByPrimaryKey(townId);
        modelAndView.setViewName("redirect:farmerCenter");
        return modelAndView;
    }

    @PostMapping("/addGood")
    public ModelAndView addGood(ModelAndView modelAndView, Good good) {
        goodService.insertSelective(good);
        modelAndView.setViewName("redirect:farmerCenter");
        return modelAndView;
    }

    @PostMapping("/deleteGood")
    public ModelAndView deleteGood(ModelAndView modelAndView, Integer goodId) {
        goodService.deleteByPrimaryKey(goodId);
        modelAndView.setViewName("redirect:farmerCenter");
        return modelAndView;
    }


}
