package com.gblau.controller.farmer;

import com.gb.common.model.po.Good;
import com.gb.common.model.po.Town;
import com.gb.common.model.po.User;
import com.gblau.controller.base.BaseController;
import com.gblau.service.GoodService;
import com.gblau.service.StoreService;
import com.gblau.service.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author gblau
 * @date 2019-05-13
 */
@RestController
@RequestMapping("/farmer")
public class FarmerCenterController extends BaseController {

    @Autowired
    private TownService townService;
    @Autowired
    private GoodService goodService;
    @Autowired
    private HttpSession session;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private StoreService storeService;

    @PostMapping("/addTown")
    public ModelAndView addTown(ModelAndView modelAndView, Town town, MultipartFile file) {
        if (file == null || file.isEmpty()) {
            modelAndView.addObject("message", "请上传文件");
            return modelAndView;
        }

        try {
            town.setImage(uploadFile(file, request));
        } catch (IOException e) {
            modelAndView.addObject("message", "文件上传遇到错误");
            e.printStackTrace();
            return modelAndView;
        }

        User user = (User) session.getAttribute("currentUser");
        town.setUserId(user.getId());
        townService.insertSelective(town);
        modelAndView.setViewName("redirect:farmerCenter");
        return modelAndView;
    }

    @GetMapping("/deleteTown")
    public ModelAndView deleteTown(ModelAndView modelAndView, Integer townId) {
        townService.deleteByPrimaryKey(townId);
        modelAndView.setViewName("redirect:farmerCenter");
        return modelAndView;
    }

    @PostMapping("/addGood")
    public ModelAndView addGood(ModelAndView modelAndView, Good good, MultipartFile file) {
        modelAndView.setViewName("redirect:farmerCenter");

        if (file == null || file.isEmpty()) {
            modelAndView.addObject("message", "请上传文件");
            return modelAndView;
        }

        try {
            good.setImage(uploadFile(file, request));
        } catch (IOException e) {
            e.printStackTrace();
            modelAndView.addObject("message", "文件上传遇到错误");
            return modelAndView;
        }
        User user = (User) session.getAttribute("currentUser");
        if (user.getId() == 20)
            good.setStoreId(1);
        if (user.getId() == 18)
            good.setStoreId(2);
        if (user.getId() == 19)
            good.setStoreId(3);
        if (user.getId() == 17)
            good.setStoreId(4);
        goodService.insertSelective(good);
        return modelAndView;
    }

    @GetMapping("/deleteGood")
    public ModelAndView deleteGood(ModelAndView modelAndView, Integer goodId) {
        goodService.deleteByPrimaryKey(goodId);
        modelAndView.setViewName("redirect:farmerCenter");
        return modelAndView;
    }


}
