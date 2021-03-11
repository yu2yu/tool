package com.yy.mq.fanout;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yy.mq.util.MqUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @description: 广播模式 需要使用交换机
 * @author: yy
 * @date: 2021/3/11
 */
public class Provider {

    public static void main(String[] args) {

        try(Connection connection = MqUtil.getConnection();
            Channel channel = connection.createChannel();){
            // 声明交换机
            channel.exchangeDeclare("orders", BuiltinExchangeType.FANOUT);
            // 发布消息到交换机
            channel.basicPublish("orders","",null,"fanout message".getBytes());

        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }

    }

}
