package com.example.demo.mq;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

public class Sender {

    public static final String  MQINFO_QUEUE                      ="mq.kettle.revert.queue";
    public static final String  MQINFO_ROUTINGKEY                 ="mq.kettle.revert.routingKey";
    public static final String  MQINFO_EXCHANGE                    ="amq.direct";

    public static void main(String[] args){

        try {
            send2MQ();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void send2MQ(String url,Integer port,String username,String password,String title,Integer type,String content){
        ConnectionFactory connectionFactory = setConnectionFactory(url, port, username, password);
        //3.通过connectionFactory创建一个连接connection
        Connection connection = null;
        try {
            connection = connectionFactory.newConnection();
            //4.通过connection创建一个频道channel
            Channel channel = connection.createChannel();
            //5.通过channel指定一个队列
            channel.queueDeclare(MQINFO_QUEUE, false, false, false, null);
            //发送的消息
            MQIoFoDto mqIoFoDto = new MQIoFoDto(title,type,content);
            String message = JSON.toJSONString(mqIoFoDto);
//        String message = "welcome to china";
            //6.通过channel向队列中添加消息，第一个参数是转发器，使用空的转发器（默认的转发器，类型是direct）
            channel.basicPublish(MQINFO_EXCHANGE, MQINFO_ROUTINGKEY, null, message.getBytes());

//            channel.basicPublish("", MQINFO_QUEUE, null, message.getBytes());
            System.out.println("向" + MQINFO_QUEUE + "中添加了一条消息:" + message);
            //7.关闭频道
            channel.close();
            //8.关闭连接
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void send2MQ() throws  Exception{
        //1.创建一个ConnectionFactory连接工厂connectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //2.通过connectionFactory设置RabbitMQ所在IP等信息
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672); //指定端口
        connectionFactory.setUsername("guest");//用户名
        connectionFactory.setPassword("guest");//密码
//        connectionFactory.setVirtualHost("//");
//        String uri = "amqp://hexin:hexin@10.10.38.200:5672/%2f";
//        connectionFactory.setUri(uri);
        //3.通过connectionFactory创建一个连接connection
        Connection connection = null;
        try {
            connection = connectionFactory.newConnection();
            //4.通过connection创建一个频道channel
            Channel channel = connection.createChannel();
            //5.通过channel指定一个队列
            channel.queueDeclare(MQINFO_QUEUE, true, false, false, null);
            //发送的消息
//            MQIoFoDto mqIoFoDto = new MQIoFoDto(title,type,content);
//            String message = JSON.toJSONString(mqIoFoDto);
        String message = "welcome to china";
            //6.通过channel向队列中添加消息，第一个参数是转发器，使用空的转发器（默认的转发器，类型是direct）
            channel.basicPublish(MQINFO_EXCHANGE, MQINFO_ROUTINGKEY, null, message.getBytes());
            System.out.println("向" + MQINFO_QUEUE + "中添加了一条消息:" + message);
            //7.关闭频道
            channel.close();
            //8.关闭连接
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static ConnectionFactory setConnectionFactory(String url,Integer port,String username,String password){
        //1.创建一个ConnectionFactory连接工厂connectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //2.通过connectionFactory设置RabbitMQ所在IP等信息
        connectionFactory.setHost(url);
        connectionFactory.setPort(port); //指定端口
        connectionFactory.setUsername(username);//用户名
        connectionFactory.setPassword(password);//密码
        return connectionFactory;
    }
}
