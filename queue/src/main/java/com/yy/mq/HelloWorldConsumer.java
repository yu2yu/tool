package com.yy.mq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.yy.mq.util.MqUtil;

import java.io.IOException;

/**
 * @description:
 * @author: yy
 * @date: 2021/3/9
 */
public class HelloWorldConsumer {

    public static void main(String[] args) {
        try (Connection connection = MqUtil.getConnection();
             Channel channel = connection.createChannel();) {
            channel.queueDeclare("hello", false, false, false, null);
            while (true) {
                channel.basicConsume("hello", new DefaultConsumer(channel){
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope,
                                               AMQP.BasicProperties properties, byte[] body) throws IOException {
                        System.out.println("=====" + new String(body));
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
