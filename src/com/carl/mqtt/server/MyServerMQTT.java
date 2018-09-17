package com.carl.mqtt.server;

import com.carl.protobuf.proto.PersonEntity;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Created by user on 2018/6/9.
 */
public class MyServerMQTT {
    private static String HOST = "tcp://192.168.13.98:1883";
//    private static String clientid = "server11";
    private static String userName = "mosquitto";
    private static String passWord = "pilot@mosquitto";
    public static void main(String[] args) throws MqttException {
        String clientid = "server11";
        final MqttClient client = new MqttClient(HOST, clientid, new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setUserName(userName);
        options.setPassword(passWord.toCharArray());
        // 设置超时时间
        options.setConnectionTimeout(10);
        // 设置会话心跳时间
        options.setKeepAliveInterval(20);

        client.setCallback(new PushCallback());
        client.connect(options);

        Thread t1 = new Thread() {

            @Override
            public void run() {
                PersonEntity.Person.Builder builder = PersonEntity.Person.newBuilder();
                builder.setId(1);
                builder.setName("ant");
                builder.setEmail("ghb@soecode.com");
                PersonEntity.Person person = builder.build();

                try {
                    int i = 0;
                    while (true) {
//                        client.publish("topic11", ("hello,topic11:>" + i).getBytes(), 1, true);
                        client.publish("topic11", person.toByteArray(), 1, true);
                        try {
                            Thread.sleep(10000);
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

        client.subscribe("topic11", 1);
//        t1.start();
//        MqttTopic topic = client.getTopic("topic11");
//        MqttDeliveryToken token = topic.publish(("hello,topic11:>"+0).getBytes(),1,true);
////        token.waitForCompletion();

    }


}
