package com.carl.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 开启WebSocket支持
 * Created by user on 2018/11/24.
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
    /*
    SpringBoot2.0集成WebSocket，实现后台向前端推送信息
    https://blog.csdn.net/moshowgame/article/details/80275084
     */
}
