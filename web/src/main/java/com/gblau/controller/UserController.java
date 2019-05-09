package com.gblau.controller;

import com.gb.common.model.po.User;
import com.gb.common.model.po.UserRole;
import com.gb.common.model.vo.ResponseModel;
import com.gblau.controller.base.BaseController;
import com.gblau.service.authorization.UserRoleService;
import com.gblau.service.authorization.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * @author gblau
 * @date 2016-11-12
 */
@RestController
@RequestMapping("/")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;


    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView){
        modelAndView.setViewName("login");
        return modelAndView;
    }

    /**
     * http://localhost:8080/login/validate
     * 登录用户接口。
     * @param currentUser
     * @return
     */
    @PostMapping("/login")
    public ModelAndView login(ModelAndView modelAndView, @Valid User currentUser, BindingResult bindingResult){

        modelAndView.setViewName("login");
        if(bindingResult.hasErrors()){
            modelAndView.addObject("error",bindingResult.getFieldError().getDefaultMessage());
            return modelAndView;
        }

        UsernamePasswordToken token = new UsernamePasswordToken(currentUser.getUsername(),currentUser.getPassword());
        token.setRememberMe(true);

        try {
            SecurityUtils.getSubject().login(token);
            modelAndView.setViewName("home");
            return modelAndView;
        } catch (AuthenticationException e) {
            token.clear();
            modelAndView.addObject("error","用户名或密码错误");
            return modelAndView;
        }
    }


    @PostMapping("/regist")
    @Transactional
    public ModelAndView doRegist(ModelAndView modelAndView, @Valid User currentUser, String roleid, BindingResult bindingResult) {
        modelAndView.setViewName("regist");
        if(bindingResult.hasErrors()){
            modelAndView.addObject("error",bindingResult.getFieldError().getDefaultMessage());
            return modelAndView;
        }

        if (userService.findUserByUsername(currentUser.getUsername()) != null) {
            modelAndView.setViewName("regist");
            modelAndView.addObject("error", "该用户名已被注册");
            modelAndView.addObject("user", currentUser);
            return modelAndView;
        }

        userService.insert(currentUser);
        UserRole ur = new UserRole().setUserId(currentUser.getId())
                                    .setRoleId(roleid);
        userRoleService.insert(ur);

        modelAndView.addObject("error", "注册成功");
        modelAndView.setViewName("login");
        return modelAndView;
    }

    /**
     * 注销登录
     */
    @RequestMapping("/logout")
    public ResponseModel doLogout() {
        SecurityUtils.getSubject().logout();
        return ResponseModel.ok().build();
    }
}
