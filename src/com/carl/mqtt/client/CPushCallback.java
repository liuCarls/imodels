package com.carl.mqtt.client;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnect;

import java.util.Arrays;
import java.util.Random;

/**
 * 发布消息的回调类
 *
 * 必须实现MqttCallback的接口并实现对应的相关接口方法CallBack 类将实现 MqttCallBack。
 * 每个客户机标识都需要一个回调实例。在此示例中，构造函数传递客户机标识以另存为实例数据。
 * 在回调中，将它用来标识已经启动了该回调的哪个实例。
 * 必须在回调类中实现三个方法：
 *
 *  public void messageArrived(MqttTopic topic, MqttMessage message)接收已经预订的发布。
 *
 *  public void connectionLost(Throwable cause)在断开连接时调用。
 *
 *  public void deliveryComplete(MqttDeliveryToken token))
 *  接收到已经发布的 QoS 1 或 QoS 2 消息的传递令牌时调用。
 *  由 MqttClient.connect 激活此回调。
 *
 */
public class CPushCallback implements MqttCallback {
    ClientMQTT server;
    public CPushCallback() {

    }

    public CPushCallback(ClientMQTT server) {
        this.server = server;
    }
    @Override
    public void connectionLost(Throwable cause) {
        // 连接丢失后，一般在这里面进行重连
//        System.out.println(Thread.currentThread().getName()+"连接断开，可以做重连");
        System.out.println("[MQTT] 连接断开，5S之后尝试重连...");
        int i=0;
        while(true) {
            try {
                Thread.sleep(5*1000);
                System.out.println("第i次重连"+(++i));
                server.reConnect();
                break;
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
        System.out.println("重连完成...");
    }
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("deliveryComplete---------" + token.isComplete());
    }
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // subscribe后得到的消息会执行到这里面
        try {
            System.out.print(Thread.currentThread().getName()+"接收消息主题 : " + topic+"\t");
            System.out.print(Thread.currentThread().getName()+"接收消息Qos : " + message.getQos()+"\t");
            System.out.println(Thread.currentThread().getName()+"接收消息内容 : " + new String(message.getPayload()));
//            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        double r = Math.random();
//        System.out.println("sv is:"+server.sv+" r is:"+r+" re:"+(r > server.sv));
//        if (r > server.sv) {
//            server.sv += 0.1;
//            System.out.println(Thread.currentThread().getName()+"throw out the error");
//            throw new Exception("TEst"); //最好不要在这个方法里面抛出异常.
//        }
    }
}