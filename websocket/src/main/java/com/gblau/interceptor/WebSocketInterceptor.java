package com.gblau.interceptor;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author gblau
 * @date 2017-05-20
 */
public class WebSocketInterceptor extends HttpSessionHandshakeInterceptor {
    /**
     *  将HttpSession中对象放入WebSocketSession中
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler handler, Map<String, Object> attributes) throws Exception {
        ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
        HttpSession session = servletRequest.getServletRequest().getSession();
        if(session!=null) {
            //区分socket连接以定向发送消息
            attributes.put("user", session.getAttribute("user"));
        }
        return super.beforeHandshake(request, response, handler, attributes);
    }
}
