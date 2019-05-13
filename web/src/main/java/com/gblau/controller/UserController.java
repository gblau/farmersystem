package com.gblau.controller;

import com.gb.common.model.po.User;
import com.gb.common.model.po.UserRole;
import com.gb.common.model.vo.ResponseModel;
import com.gb.common.util.Log;
import com.gblau.controller.base.BaseController;
import com.gblau.service.authorization.UserRoleService;
import com.gblau.service.authorization.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
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
        Log.info("username: {}", currentUser.getUsername());
        modelAndView.setViewName("login");
        if(bindingResult.hasErrors()){
            modelAndView.addObject("error",bindingResult.getFieldError().getDefaultMessage());
            return modelAndView;
        }

        UsernamePasswordToken token = new UsernamePasswordToken(currentUser.getUsername(),currentUser.getPassword());
        token.setRememberMe(true);

        try {
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            if (subject.isAuthenticated())
                return skipHomeByRole(modelAndView, currentUser.getUsername());
            else
                throw new AuthenticationException();
        } catch (AuthenticationException e) {
            token.clear();
            modelAndView.addObject("error","用户名或密码错误");
            return modelAndView;
        }
    }

    private ModelAndView skipHomeByRole(ModelAndView modelAndView, String username) {
        User user = userService.findUserByUsername(username);
        switch (user.getAthority()) {
            case 1: {
                return getAllUser(modelAndView);
            }
            case 2: {
                modelAndView.setViewName("farmerHome");
                break;
            }
            case 3: {
                modelAndView.setViewName("customerHome");
                break;
            }
        }
        return modelAndView;
    }

    @GetMapping("/regist")
    public ModelAndView regist(ModelAndView modelAndView){
        modelAndView.setViewName("register");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/regist")
    @Transactional
    public ModelAndView doRegist(ModelAndView modelAndView, @Valid User currentUser, String secondPassword, String roleid, BindingResult bindingResult) {
        Log.error("username: {}", currentUser.getUsername());
        modelAndView.setViewName("register");
        if(bindingResult.hasErrors()){
            modelAndView.addObject("error",bindingResult.getFieldError().getDefaultMessage());
            return modelAndView;
        }

        if(!currentUser.getPassword().equals(secondPassword)) {
            modelAndView.setViewName("register");
            modelAndView.addObject("error", "两次密码不一致");
            modelAndView.addObject("user", currentUser);
            return modelAndView;
        }

        if (userService.findUserByUsername(currentUser.getUsername()) != null) {
            modelAndView.setViewName("register");
            modelAndView.addObject("error", "该用户名已被注册");
            modelAndView.addObject("user", currentUser);
            return modelAndView;
        }

        currentUser.setAthority(Integer.valueOf(roleid));

        ByteSource salt = ByteSource.Util.bytes(currentUser.getUsername());
        String newPs = new SimpleHash("MD5", currentUser.getPassword(), salt, 1024).toHex();

        currentUser.setPassword(newPs);

        userService.insertSelective(currentUser);
        UserRole ur = new UserRole().setUserId(currentUser.getId())
                                    .setRoleId(Integer.valueOf(roleid));

        userRoleService.insert(ur);

        modelAndView.addObject("error", "注册成功");
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/changepwd")
    public ModelAndView changePwd(ModelAndView modelAndView){
        modelAndView.setViewName("changePwd");
        return modelAndView;
    }

    private ModelAndView getAllUser(ModelAndView modelAndView) {
        modelAndView.addObject("users", userService.findAllUsers());
        modelAndView.setViewName("adminstratorHome");
        return modelAndView;
    }

    @GetMapping("deleteUser")
    public ModelAndView deleteUser(ModelAndView modelAndView, String userId) {
        Log.error("删除用户 id: {}", userId);
        userService.deleteByPrimaryKey(Integer.valueOf(userId));
        return getAllUser(modelAndView);
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
