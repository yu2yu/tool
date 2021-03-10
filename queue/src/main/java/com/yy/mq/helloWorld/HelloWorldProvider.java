package com.yy.mq.helloWorld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.yy.mq.util.MqUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @description: 生产者
 * @author: yy
 * @date: 2021/3/9
 */
public class HelloWorldProvider {

    public static void main(String[] args) throws IOException {
        try (Connection connection = MqUtil.getConnection();
             Channel channel = connection.createChannel();) {
            channel.queueDeclare("hello", false, false, false, null);
            channel.basicPublish("", "hello", null, "hello yy!".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
