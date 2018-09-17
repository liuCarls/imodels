package com.carl.mqtt.server;

import com.carl.protobuf.proto.GatewayRtStatusProto;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Date;

/**
 * Description 服务器向多个客户端推送主题，即不同客户端可向服务器订阅相同主题
 * Created by user on 2018/6/4.
 */
public class ServerMQTT {
    //tcp://MQTT安装的服务器地址:MQTT定义的端口号
//    public static final String HOST = "tcp://192.168.13.98:1883";
    public static final String HOST = "tcp://192.168.13.101:1883";
//    public static final String HOST = "tcp://192.168.195.129:1883";
    //定义一个主题
    public static final String TOPIC = "gatecloud/gateway/general";
    //定义MQTT的ID，可以在MQTT服务配置中指定
    private static final String clientid = "server11";

    private MqttClient client;
//    private MqttAsyncClient client;
    private MqttTopic topic11;
    private String userName = "mosquitto";
    private String passWord = "pilot@mosquitto";

    private MqttMessage message;

    /**
     * 构造函数
     * @throws MqttException
     */
    public ServerMQTT() throws MqttException {
        // MemoryPersistence设置clientid的保存形式，默认为以内存保存
        client = new MqttClient(HOST, clientid, new MemoryPersistence());
//        client = new MqttAsyncClient(HOST, clientid, new MemoryPersistence());
        connect();
    }

    /**
     *  用来连接服务器
     */
    private void connect() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setUserName(userName);
        options.setPassword(passWord.toCharArray());
        // 设置超时时间
        options.setConnectionTimeout(10);
        // 设置会话心跳时间
        options.setKeepAliveInterval(20);
        try {
//            client.setCallback(new ClientPushCallback());

            client.connect(options);

            topic11 = client.getTopic(TOPIC);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param topic
     * @param message
     * @throws MqttPersistenceException
     * @throws MqttException
     */
    public void publish(MqttTopic topic , MqttMessage message) throws MqttPersistenceException,
            MqttException {
        MqttDeliveryToken token = topic.publish(message);
        token.waitForCompletion();
//        System.out.println("message is published completely! "
//                + token.isComplete());
    }

    /**
     *  启动入口
     * @param args
     * @throws MqttException
     */
    public static void main(String[] args) throws MqttException {
        ServerMQTT server = new ServerMQTT();
        int i=0;
        while(true){
////            PersonEntity.Person.Builder builder = PersonEntity.Person.newBuilder();
//            builder.setId(1);
//            builder.setName("ant"+i);
//            builder.setEmail("ghb@soecode.com");

            GatewayRtStatusProto.GatewayRtStatus.Builder builder = GatewayRtStatusProto.GatewayRtStatus.newBuilder();
//            builder.setSN("gw201806102"+Math.round(Math.random()*10));
            builder.setSN("gw2018061025");
            builder.setRecTimeSeconds((int) (new Date()).getTime());

            builder.setGatewayType(5);
            builder.setVersionGateway("2.5.1-1802075");
            builder.setCpuUsage(123456);
//            builder.setMemUsage(null);
            builder.setNetMode(1);
//            builder.setIccid("IIIDDCCCString");
            builder.setExtendParam("eeeeextendparamString");
            GatewayRtStatusProto.GatewayRtStatus grInfo = builder.build();
            server.message = new MqttMessage();
            server.message.setQos(0);
            server.message.setRetained(false);
//            System.out.println(new String(person.toByteArray()));
            byte[] header = new byte[28];
            header[27]=1;
            byte[] load = grInfo.toByteArray();
            byte[] dd = new byte[header.length+load.length];
            System.arraycopy(header,0,dd,0, 26);
            System.arraycopy(intToByteArray(1,false),0,dd,26,2);
//            if(Math.random()>0){
//            } else {
//                System.arraycopy(intToByteArray(2,false),0,dd,26,2);
//            }
            System.arraycopy(load,0,dd,header.length, load.length);
//            System.out.println(Arrays.toString(header));
//            System.out.println(Arrays.toString(load));
//            System.out.println(Arrays.toString(dd));
            server.message.setPayload(dd);
            server.publish(server.topic11 , server.message);
            System.out.println(server.message.isRetained() + "------ratained状态");

            try {
                Thread.sleep(1000*60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
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
    public static byte[] intToByteArray(int a, boolean isFull) {
        if(isFull){
            return new byte[] {
                    (byte) ((a >> 24) & 0xFF),
                    (byte) ((a >> 16) & 0xFF),
                    (byte) ((a >> 8) & 0xFF),
                    (byte) (a & 0xFF)};
        } else {
            return new byte[]{
                    (byte) ((a >> 8) & 0xFF),
                    (byte) (a & 0xFF)};
        }
    }
}
