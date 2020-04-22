package com.example.demo.mq;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class Receiver {

    //队列名称
    public static final String  MQINFO_QUEUE                      ="mq.kettle.revert.queue";
    public static final String  MQINFO_ROUTINGKEY                 ="mq.kettle.revert.routingKey";
    public static final String  MQINFO_EXCHANGE                    ="amq.direct";

    public static void main(String[] argv) throws Exception {
        //1.创建一个ConnectionFactory连接工厂connectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //2.通过connectionFactory设置RabbitMQ所在IP等信息
//        connectionFactory.setHost("10.10.38.200");
//        connectionFactory.setPort(5672); //指定端口
//        connectionFactory.setUsername("hexin");//用户名
//        connectionFactory.setPassword("hexin");//密码
//        connectionFactory.setVirtualHost("/");
        String uri = "amqp://hexin:hexin@10.10.38.200:5672/%2f";
        connectionFactory.setUri(uri);
        //3.通过connectionFactory创建一个连接connection
        Connection connection = connectionFactory.newConnection();
        //4.通过connection创建一个频道channel
        Channel channel = connection.createChannel();
        //5.通过channel指定队列
        channel.queueDeclare(MQINFO_QUEUE, true, false, false, null);
        channel.exchangeDeclare(MQINFO_EXCHANGE, "direct", true);
        channel.queueBind(MQINFO_QUEUE, MQINFO_EXCHANGE, MQINFO_ROUTINGKEY);
        //与发送消息不同的地方
        //6.创建一个消费者队列consumer,并指定channel
        QueueingConsumer consumer = new QueueingConsumer(channel);
        //7.为channel指定消费者
        channel.basicConsume(MQINFO_QUEUE, true, consumer);
        while (true) {
            //从consumer中获取队列中的消息,nextDelivery是一个阻塞方法,如果队列中无内容,则等待
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("接收到了" + MQINFO_QUEUE + "中的消息:" + message);
        }

    }

    private class ClosedChannelComsumer extends QueueingConsumer {

        /**
         * 队列信息
         */
        private MQIoFoDto       mqInfo;
        public ClosedChannelComsumer(Channel ch1, MQIoFoDto mqInfo){
            super(ch1);
            this.mqInfo = mqInfo;
        }
    }
}
