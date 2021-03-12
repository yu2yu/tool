package com.yy.mq.topic;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.yy.mq.util.MqUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @description:
 * @author: yy
 * @date: 2021/3/12
 */
public class Consumer2 {

    public static void main(String[] args) {
        try{

            String str = "two ";
            String exchangeName = "orders";
            String routingKey = "svip.*";

            Connection connection = MqUtil.getConnection();
            Channel channel = connection.createChannel();
            // 创建临时队列
            String queue = channel.queueDeclare().getQueue();
            channel.queueBind(queue,exchangeName,routingKey);

            channel.basicConsume(queue, true, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,
                                           AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println(str + " Received" + new String(body,"UTF-8"));
                }
            });
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }
}
