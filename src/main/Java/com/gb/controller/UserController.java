package com.gb.controller;

import com.gb.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gblau on 2016-11-12.
 */
@RestController
public class UserController {

    /**
     * http://localhost:8080/login/validate
     * 登录用户接口。
     * @param currentUser
     * @return
     */
    @RequestMapping("/login/validate")
    public boolean doLogin(User currentUser) {
        Subject user = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(currentUser.getUsername(),currentUser.getPassword());
        token.setRememberMe(true);
        try {
            user.login(token);
            return true;
        }catch (AuthenticationException e) {
            e.printStackTrace();
            token.clear();
            return false;
        }
    }


}
