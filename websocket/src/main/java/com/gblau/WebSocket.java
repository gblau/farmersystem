package com.gblau;

import com.gblau.handler.DefaultTextWebSocketHandler;
import com.gblau.handler.SocketHandler;
import com.gblau.interceptor.WebSocketInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


/**
 * @author gblau
 * @date 2017-05-20
 */
@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocket extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
    @Autowired
    private DefaultTextWebSocketHandler socketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //注册处理拦截器,拦截url为socketServer的请求
        registry.addHandler(socketHandler, "/socketServer").addInterceptors(new WebSocketInterceptor());

        //注册SockJs的处理拦截器,拦截url为/sockjs/socketServer的请求
        registry.addHandler(socketHandler, "/sockjs/socketServer").addInterceptors(new WebSocketInterceptor()).withSockJS();
    }
}
