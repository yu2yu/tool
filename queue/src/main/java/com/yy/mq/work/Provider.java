package com.yy.mq.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yy.mq.util.MqUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @description:
 * @author: yy
 * @date: 2021/3/10
 */
public class Provider {

    public static void main(String[] args) throws Exception {
        Connection connection = MqUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work",false,false,false,null);
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("","work",null,(i+"").getBytes());
        }
        MqUtil.closeConnection(connection,channel);
    }

}
