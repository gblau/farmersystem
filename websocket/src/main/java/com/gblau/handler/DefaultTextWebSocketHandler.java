package com.gblau.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.WebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 一个用于处理接收到的信息的SocketHandler。
 * Spring4提供{@link WebSocketHandler}对websocket的支持，并提供一个什么也不做的默认实现
 * 可以选择限制二进制（Binary）或者限制文本（Text）信息。
 * @see TextWebSocketHandler 禁用了对Binary信息的处理
 * @see BinaryWebSocketHandler 禁用了对Text信息的处理
 * @see AbstractWebSocketHandler 什么也不做的默认实现
 * @author gblau
 * @date 2017-08-10
 */
@Controller
@RequestMapping("/websocket")
public class DefaultTextWebSocketHandler extends TextWebSocketHandler {
    private static final Map<String, WebSocketSession> users = new HashMap<>();
    private static final Logger log = LoggerFactory.getLogger(DefaultTextWebSocketHandler.class);

    /**
     * 建立连接后调用这个方法。默认实现是什么也不做
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session)
            throws Exception {
        log.info("成功建立socket连接");
        users.put(getSessionUsername(session), session);
        session.sendMessage(new TextMessage("我们已经成功建立soket通信了"));
    }

    /**
     * 处理具体的信息，有text和binary两种类型，可以选择两种都支持，也可以选择禁用一种。
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        TextMessage returnMessage = new TextMessage(message.getPayload() + " recieved at server");
        session.sendMessage(returnMessage);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable error)
            throws Exception {
        if(session.isOpen()){
            session.close();
        }
        log.error("连接出现错误:"+error.toString());
        users.remove(getSessionUsername(session));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus arg1)
            throws Exception {
        log.debug("连接已关闭");
        users.remove(getSessionUsername(session));
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        try {
            for (Map.Entry user : users.entrySet())
                sendMessage((WebSocketSession) user.getValue(), message);
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
        }
    }

    /**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     */
    public void sendMessageToUser(String userName, TextMessage message) {
        for (Map.Entry userEntry : users.entrySet()) {
            WebSocketSession user = (WebSocketSession) userEntry;
            if (user.getAttributes().get("user").equals(userName)) {
                try {
                    sendMessage(user, message);
                } catch (IOException e) {
                    log.error(e.getLocalizedMessage());
                }
                break;
            }
        }
    }

    private void sendMessage(WebSocketSession user, TextMessage message) throws IOException {
        if (user.isOpen())
            user.sendMessage(message);
    }

    private String getSessionUsername(WebSocketSession session) {
        return session.getAttributes().get("user").toString();
    }
}