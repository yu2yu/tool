package com.yy.mq.routing;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yy.mq.util.MqUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @description:
 * @author: yy
 * @date: 2021/3/12
 */
public class Provider {

    public static void main(String[] args) {

        String exchangeName = "orders";
        String routingKey = "vip2";

        try(Connection connection = MqUtil.getConnection();
            Channel channel = connection.createChannel();){
            // 声明交换机
            channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT);
            // 发布消息到交换机
            channel.basicPublish(exchangeName,routingKey,null,"direct message".getBytes());

        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }

    }
}
