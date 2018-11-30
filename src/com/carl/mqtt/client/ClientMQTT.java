package com.carl.mqtt.client;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import sun.util.resources.cldr.te.CurrencyNames_te;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by user on 2018/6/4.
 */
public class ClientMQTT {
//    public static final String HOST = "tcp://192.168.13.98:1883";
    public static final String HOST = "tcp://192.168.13.101:1883";
//    public static final String HOST = "tcp://192.168.8.217:1883";

    public final String TOPIC = "topic11";
    private final String clientid = "client19";
    public MqttClient client;
    private MqttConnectOptions options;
    private String userName = "mosquitto";
    private String passWord = "pilot@mosquitto";
    public double sv = 0.7;

    private void start() {
        try {
            // host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
            client = new MqttClient(HOST, clientid, new MemoryPersistence());
            // MQTT的连接设置
            options = new MqttConnectOptions();
            // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，
            // 这里设置为true表示每次连接到服务器都以新的身份连接
            options.setCleanSession(false);
//            // 设置连接的用户名
            options.setUserName(userName);
//            // 设置连接的密码
            options.setPassword(passWord.toCharArray());

            options.setMqttVersion(4);
//            options.setMaxInflight();
            // 设置连接超时时间 单位为秒
            options.setConnectionTimeout(10);
            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
            options.setKeepAliveInterval(20);
            // 设置回调
            client.setCallback(new CPushCallback(this));
//            MqttTopic topic = client.getTopic(TOPIC);
            //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
            //options.setWill("topic12", "close".getBytes(), 2, true); //这个客户端断开时发送此消息
            //设置COMMAND_TIMEOUT超时时间
//            client.setTimeToWait(1000);
            client.connect(options);
            //订阅消息
            int[] Qos  = {2};
            String[] topic1 = {TOPIC};
            client.subscribe(topic1, Qos);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void getOption() {
//        options = new MqttConnectOptions();
//        options.setCleanSession(false);
//        options.setConnectionTimeout(10);
//        options.setKeepAliveInterval(20);
//
//    }
    public void reConnect() throws MqttException {
        System.out.println("Connect:"+Thread.currentThread().getName());
//        client = new MqttClient(HOST, clientid+ Math.round(Math.random()*20), new MemoryPersistence());
//        client = new MqttClient(HOST, clientid, new MemoryPersistence());
//        client.setCallback(new CPushCallback(this));
//        client.close();
//
//        options.setCleanSession(false);
        client.connect(options);
        int[] Qos  = {2};
        String[] topic1 = {TOPIC};
        client.subscribe(topic1, Qos);


//        if(!client.isConnected()){
//        }
//        try {
//            synchronized (this) {
//            if (client == null) {
//                client = new MqttClient(HOST, clientid, new MemoryPersistence());
//                client.setCallback(new CPushCallback(this));
//            }
//            int i = 0;
//            while(!client.isConnected()){
//
//                client.connect(options);
//                if (!client.isConnected()) {
//                    Thread.sleep(1000*10);
//                }
//            }
//                if (client.isConnected()) {
//                    int[] Qos  = {2};
//                    String[] topic1 = {TOPIC};
//                    client.subscribe(topic1, Qos);
//                }

//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }

    public static void main(String[] args) throws Exception {
        ClientMQTT server = new ClientMQTT();
        server.start();

//        server.client.close();
        Thread.sleep(1000*15);
        server.client.disconnect(); //断开连接
        Thread.sleep(1000*20);
        server.client.connect(server.options); //重连


    }
}
