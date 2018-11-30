package com.carl.mqtt.server;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Created by user on 2018/6/9.
 */
public class MyServerMQTT {
//    private static String HOST = "tcp://192.168.13.98:1883";
    private static String HOST = "tcp://192.168.13.101:1883";
//    private static String clientid = "server11";
    private static String userName = "mosquitto";
    private static String passWord = "pilot@mosquitto";
    public static void main(String[] args) throws MqttException {
        String clientid = "server11";
        final MqttClient client = new MqttClient(HOST, clientid, new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
//        options.setUserName(userName);
//        options.setPassword(passWord.toCharArray());
        // 设置超时时间
        options.setConnectionTimeout(10);
        // 设置会话心跳时间
        options.setKeepAliveInterval(20);

        client.setCallback(new SPushCallback());
        client.connect(options);
        //Qos消息质量对于
        Thread t1 = new Thread() {

            @Override
            public void run() {
            try {
                int i = 0;
                while (true) {
//                        client.publish("topic11", ("hello,topic11:>" + i).getBytes(), 1, true);
                    //retained是否设置保留消息，若为true，后来的订阅者订阅该主题时仍可接收到该消息
                    //若订阅某主题的客户端重启，则会把此主题之前发布的消息重新推送到客户端。
                    MqttMessage message = new MqttMessage();
                    message.setPayload(("Msg"+i).getBytes());
//                    message.setRetained(false);
                    message.setQos(2);

                    client.publish("topic11",message);
//                    client.publish("topic11", , 2, false);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
            } catch (MqttException e) {
                e.printStackTrace();
            }
            }
        };

//        client.subscribe("topic12", 0);
        t1.start();
//        MqttTopic topic = client.getTopic("topic11");
//        MqttDeliveryToken token = topic.publish(("hello,topic11:>"+0).getBytes(),1,true);
////        token.waitForCompletion();

    }


}
