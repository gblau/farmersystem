package com.gblau.controller;

import com.gblau.handler.DefaultTextWebSocketHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpSession;

/**
 * @author gblau
 * @date 2017-09-02
 */
@Controller
public class SocketController{

    private static final Logger logger = LoggerFactory.getLogger(SocketController.class);

    @Autowired
    private DefaultTextWebSocketHandler socketHandler;

    @RequestMapping(value="/connect", method = RequestMethod.GET)
    public String login(HttpSession session){
        logger.info("用户登录了建立连接啦");

        session.setAttribute("user", "gblau");

        return "home";
    }

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String sendMessage(HttpSession session){
        socketHandler.sendMessageToUser("gblau", new TextMessage("这是一条测试的消息"));
        return "message";
    }

}
