package com.carl.protobuf.test;

import com.googlecode.protobuf.format.JsonFormat;
import com.carl.protobuf.proto.PersonEntity;
import com.carl.protobuf.proto.PersonEntity.Person;

import java.io.IOException;

/**
 * Created by user on 2018/6/5.
 */
public class ProtoTest {
    public static void main(String[] args) throws IOException {
        //模拟将对象转成byte[]，方便传输
        Person.Builder builder = Person.newBuilder();
        builder.setId(1);
        builder.setName("ant");
        builder.setEmail("ghb@soecode.com");
        Person person = builder.build();
        System.out.println("before :"+ person.toString());

        System.out.println("===========Person Byte==========");
        System.out.println(person.toByteString());
        byte[] byteArray =person.toByteArray();
        for(byte b : byteArray){
            System.out.print(b);
        }
        System.out.println();
        System.out.println("=========byte to protoful=======================");
        //模拟接收Byte[]，反序列化成Person类
        Person p2 = Person.parseFrom(byteArray);
        System.out.println("after :" +p2.toString());


        System.out.println("===========protoful to JSON=====================");
//        Message someProto = SomeProto.getDefaultInstance();
        String jsonP = JsonFormat.printToString(p2);
        System.out.println(jsonP);


        System.out.println("=============JSON to protoful===================");
        Person.Builder builder1 = Person.newBuilder();
        String jsonFormat = "{\"id\": 1,\"name\": \"ant\",\"email\": \"ghb@soecode.com\"}";
        JsonFormat.merge(jsonFormat, builder1);
        Person person1 = builder1.build();
        System.out.println("Last :\n"+ person1.toString());
    }
}
