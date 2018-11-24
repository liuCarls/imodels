package com.carl.controller;

//import org.springframework.stereotype.Component;
//
//import javax.websocket.OnClose;
//import javax.websocket.OnMessage;
//import javax.websocket.OnOpen;
//import javax.websocket.Session;
//import javax.websocket.server.ServerEndpoint;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by user on 2018/11/24.
 */
@ServerEndpoint(value = "/ws/test")
//@EnableScheduling
@Component
//@Controller
public class IWebSocketServer {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<IWebSocketServer> webSocketSet
            = new CopyOnWriteArraySet<IWebSocketServer>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void open(Session session) {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        //在线数加1
        onlineCount++;
        System.out.println("*****有新连接加入！当前在线人数为" + onlineCount);
        try {
//            sendMessage(CommonConstant.CURRENT_WANGING_NUMBER.toString());
            sendMessage("abc");
        } catch (IOException e) {
            System.out.println("IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void close() {
        webSocketSet.remove(this);  //从set中删除
        //在线数减1
        onlineCount--;
        System.out.println("*****有一连接关闭！当前在线人数为");
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void message(String message, Session session) {
        System.out.println("*****来自客户端的消息:" + message);
    }

    /**
     * 发生错误时调用
     * */
     @OnError
     public void error(Session session, Throwable error) {
         System.out.println("*****发生错误");
         error.printStackTrace();
     }




     public void sendMessage(String message) throws IOException {
         DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.session.getBasicRemote().sendText(df.format(new Date())+ "\t NUM:"+onlineCount);
//        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
     }


     /**
      * 群发自定义消息
      * */
     @Scheduled(cron="0/5 * *  * * ? ")
    public void sendInfo(String message) throws IOException {
        for (IWebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

}

/*
 * 核心是@ServerEndpoint这个注解。这个注解是Javaee标准里的注解，
 * tomcat7以上已经对其进行了实现，如果是用传统方法使用tomcat发布项目，
 * 只要在pom文件中引入javaee标准即可使用。
 * <dependency>
 <groupId>javax</groupId>
 <artifactId>javaee-api</artifactId>
 <version>7.0</version>
 <scope>provided</scope>
 </dependency>
 *
 *
 * 但使用springboot的内置tomcat时，就不需要引入javaee-api了，
 * spring-boot已经包含了。使用springboot的websocket功能首先引入springboot组件。
 *  <dependency>
 <groupId>org.springframework.boot</groupId>
 <artifactId>spring-boot-starter-websocket</artifactId>
 <version>1.3.5.RELEASE</version>
 </dependency>
 */
