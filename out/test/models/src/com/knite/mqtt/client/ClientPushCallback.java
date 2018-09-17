package com.carl.mqtt.client;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Arrays;

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
public class ClientPushCallback implements MqttCallback {

    public void connectionLost(Throwable cause) {
        // 连接丢失后，一般在这里面进行重连
        System.out.println("连接断开，可以做重连");
    }

    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("deliveryComplete---------" + token.isComplete());
    }

    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // subscribe后得到的消息会执行到这里面
        try {
            System.out.print("接收消息主题 : " + topic+"\t");
//        System.out.println("接收消息Qos : " + message.getQos());
//        System.out.println("接收消息内容 : " + PersonEntity.Person.parseFrom(message.getPayload()).toString());
//        System.out.println("接收消息内容 : " + message.getPayload().toString());

            byte[] byteArray = message.getPayload();
//        System.out.println(Arrays.toString(byteArray));
            if (topic.startsWith("gatecloud/gateway")) {
                int[] fWidth = {4, 2, 20, 2, byteArray.length - 28};
                int p = 0;
                int i = 0;
                byte[] load = null;
                int fun = 0;
                String sn = null;
                while (i < fWidth.length) {
                    byte[] tmp = Arrays.copyOfRange(byteArray, p, p + fWidth[i]);
                    p = p + fWidth[i];
                    switch (i) {
                        case 0:
                        case 1:
//                            System.out.println(Arrays.toString(tmp));
                            break;
                        case 2:
                            int location = 0;
                            for (byte b : tmp) {
                                location++;
                                if (b == 0) {
                                    location--;
                                    break;
                                }
                            }
                            byte[] tmp1 = new byte[location - 1];
                            System.arraycopy(tmp, 0, tmp1, 0, location - 1);
                            sn = new String(tmp1);
                            break;
                        case 3:
                            fun = byteArrayToInt(tmp);
                            break;
                        case 4:
//                    System.out.println(Arrays.toString(tmp));
                            load = tmp;
                    }
                    i++;
                }
                System.out.println("网关发送---sn:" + sn + "fun:" + fun);
            } else {
                int[] fWidth = {4, 2, 2, byteArray.length - 8};
                int p = 0;
                int i = 0;
                byte[] load = null;
                int fun = 0;
                String sn = null;
                while (i < fWidth.length) {
                    byte[] tmp = Arrays.copyOfRange(byteArray, p, p + fWidth[i]);
                    p = p + fWidth[i];
                    switch (i) {
                        case 0:
                        case 1:
//                            System.out.println(Arrays.toString(tmp));
                            break;

                        case 2:
                            fun = byteArrayToInt(tmp);
                            break;
                        case 3:
//                    System.out.println(Arrays.toString(tmp));
                            load = tmp;
                    }
                    i++;
                }
                System.out.println("服务发送---sn:" + sn + "fun:" + fun);
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }



    }

    public static int byteArrayToInt(byte[] bs) {
        int len = bs.length;
        len = len>4?4:len; //如果字节长度大于4,则转换byte数组的前4个字节
        int o=0;
        for(int i=1;i<=len;i++){
            if(i==1){
                o=(bs[len-i]&0xFF) << 8*(i-1);
            } else{
                o = o|((bs[len-i]&0xFF) << 8*(i-1));
            }
        }
        return o;
    }
}