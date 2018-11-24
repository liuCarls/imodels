package com.carl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by user on 2018/11/5.
 */
//RestController就是controller里面的方法都以json格式输出，不用再写什么jackjson配置的了！
@RestController
public class HomeCtr {
    @Autowired
    IWebSocketServer webSocket;

    @RequestMapping("/home")
    public String home() {
        return "abc";
    }
    @RequestMapping("/send")
    public void websocket() {
        try {
            webSocket.sendInfo("abc");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
