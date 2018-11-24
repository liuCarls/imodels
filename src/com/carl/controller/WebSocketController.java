package com.carl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by user on 2018/11/24.
 */
//@Controller
//@EnableScheduling
public class WebSocketController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @MessageMapping("/send")
    @SendTo("/topic/send")
    public SocketMessage send(SocketMessage message) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        message.date = df.format(new Date());
        return message;
    }

    /*
    @EnableScheduling注解为：启用spring boot的定时任务，
    这与“callback”方法相呼应，用于每隔1秒推送服务器端的时间。
     */
    @Scheduled(fixedRate = 1000)
    @SendTo("/topic/callback")
    public Object callback() throws Exception {
        // 发现消息
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        messagingTemplate.convertAndSend("/topic/callback", df.format(new Date()));
        return "callback";
    }
}
