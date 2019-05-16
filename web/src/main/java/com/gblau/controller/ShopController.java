package com.gblau.controller;

import com.gb.common.model.po.Good;
import com.gblau.service.GoodService;
import com.gblau.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author gblau
 * @date 2019-05-17
 */
@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private GoodService goodService;
    @Autowired
    private StoreService storeService;

    @GetMapping("/amin1")
    public ModelAndView amin1(ModelAndView modelAndView) {
        List<Good> goods = goodService.findByStore(1);
        modelAndView.addObject("goods", goods);
        modelAndView.setViewName("amin01");
        return modelAndView;
    }

    @GetMapping("/amin11")
    public ModelAndView amin011(ModelAndView modelAndView) {
        modelAndView.setViewName("amin01-1");
        return modelAndView;
    }

    @GetMapping("/amin2")
    public ModelAndView amin2(ModelAndView modelAndView) {
        modelAndView.setViewName("amin02");
        return modelAndView;
    }

    @GetMapping("/jiahao")
    public ModelAndView jiahao(ModelAndView modelAndView) {
        modelAndView.setViewName("jiahao");
        return modelAndView;
    }

    @GetMapping("/jiaying")
    public ModelAndView jiaying(ModelAndView modelAndView) {
        modelAndView.setViewName("jiaying");
        return modelAndView;
    }
}
