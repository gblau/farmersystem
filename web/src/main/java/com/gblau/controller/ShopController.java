package com.gblau.controller;

import com.gb.common.model.po.Good;
import com.gb.common.model.po.Order;
import com.gb.common.model.po.Town;
import com.gblau.service.GoodService;
import com.gblau.service.OrderService;
import com.gblau.service.StoreService;
import com.gblau.service.TownService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    @Autowired
    private OrderService orderService;
    @Autowired
    private TownService townService;

    /**
     * 添加订单
     * @param modelAndView
     * @param order
     * @return
     */
    @PostMapping("addOrder")
    public ModelAndView addOrder(ModelAndView modelAndView, Order order) {
        Good good = goodService.findByPrimaryKey(order.getGoodId());
        modelAndView.addObject("good", good);

        if (order.getNum() != null && good.getStock() < order.getNum()) {
            modelAndView.setViewName("amin01-1");
            modelAndView.addObject("error", "库存不足购买数量！");
            return modelAndView;
        }

        order.setIsAccepted(new Byte("0"));
        order.setGoodId(good.getId());
        orderService.insertSelective(order);
        modelAndView.addObject("error", "购买成功！");
        modelAndView.setViewName("amin01-1");
        return modelAndView;
    }

    @GetMapping("/amin1")
    public ModelAndView amin1(ModelAndView modelAndView) {
        List<Good> goods = goodService.findByStore(1);
        modelAndView.addObject("goods", goods);
        modelAndView.setViewName("amin01");
        return modelAndView;
    }

    @RequiresAuthentication
    @GetMapping("/amin01F")
    public ModelAndView amin01F(ModelAndView modelAndView) {
        Town town = townService.findByPrimaryKey(1);
        if (town != null)
            modelAndView.addObject("maxPerson", town.getMaxPerson());
        modelAndView.setViewName("amin01F");
        return modelAndView;
    }

    @RequiresAuthentication
    @GetMapping("/amin11")
    public ModelAndView amin011(ModelAndView modelAndView, Integer goodId) {
        if (goodId == null) {
            modelAndView.setViewName("redirect:amin1");
            return modelAndView;
        }

        Good good = goodService.findByPrimaryKey(goodId);
        modelAndView.addObject("good", good);
        modelAndView.setViewName("amin01-1");
        return modelAndView;
    }

    @GetMapping("/amin2")
    public ModelAndView amin2(ModelAndView modelAndView) {
        List<Good> goods = goodService.findByStore(2);
        modelAndView.addObject("goods", goods);
        modelAndView.setViewName("amin02");
        return modelAndView;
    }

    @RequiresAuthentication
    @GetMapping("/amin21")
    public ModelAndView amin021(ModelAndView modelAndView, Integer goodId) {
        if (goodId == null) {
            modelAndView.setViewName("redirect:amin2");
            return modelAndView;
        }

        Good good = goodService.findByPrimaryKey(goodId);
        modelAndView.addObject("good", good);
        modelAndView.setViewName("amin02-1");
        return modelAndView;
    }


    @RequiresAuthentication
    @GetMapping("/amin02F")
    public ModelAndView amin02F(ModelAndView modelAndView) {
        Town town = townService.findByPrimaryKey(1);
        if (town != null)
            modelAndView.addObject("maxPerson", town.getMaxPerson());
        modelAndView.setViewName("amin02F");
        return modelAndView;
    }

    @GetMapping("/jiahao")
    public ModelAndView jiahao(ModelAndView modelAndView) {
        List<Good> goods = goodService.findByStore(3);
        modelAndView.addObject("goods", goods);
        modelAndView.setViewName("jiahao");
        return modelAndView;
    }

    @RequiresAuthentication
    @GetMapping("/jiahao1")
    public ModelAndView jiahao1(ModelAndView modelAndView, Integer goodId) {
        if (goodId == null) {
            modelAndView.setViewName("redirect:jiahao");
            return modelAndView;
        }

        Good good = goodService.findByPrimaryKey(goodId);
        modelAndView.addObject("good", good);
        modelAndView.setViewName("jiahao-1");
        return modelAndView;
    }

    @RequiresAuthentication
    @GetMapping("/jiahaoF")
    public ModelAndView jiahaoF(ModelAndView modelAndView) {
        Town town = townService.findByPrimaryKey(3);
        if (town != null)
            modelAndView.addObject("maxPerson", town.getMaxPerson());
        modelAndView.setViewName("jiahaoF");
        return modelAndView;
    }

    @GetMapping("/jiaying")
    public ModelAndView jiaying(ModelAndView modelAndView) {
        List<Good> goods = goodService.findByStore(4);
        modelAndView.addObject("goods", goods);
        modelAndView.setViewName("jiaying");
        return modelAndView;
    }

    @RequiresAuthentication
    @GetMapping("/jiaying1")
    public ModelAndView jiaying1(ModelAndView modelAndView, Integer goodId) {
        if (goodId == null) {
            modelAndView.setViewName("redirect:jiaying");
            return modelAndView;
        }

        Good good = goodService.findByPrimaryKey(goodId);
        modelAndView.addObject("good", good);
        modelAndView.setViewName("jiaying-1");
        return modelAndView;
    }

    @RequiresAuthentication
    @GetMapping("/jiayingF")
    public ModelAndView jiayingF(ModelAndView modelAndView) {
        Town town = townService.findByPrimaryKey(4);
        if (town != null)
            modelAndView.addObject("maxPerson", town.getMaxPerson());
        modelAndView.setViewName("jiayingF");
        return modelAndView;
    }

}
