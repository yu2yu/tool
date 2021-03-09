package com.yy.mq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


/**
 * @description:
 * @author: yy
 * @date: 2021/3/9
 */
public class MqUtil {

    public static Connection getConnection() throws IOException, TimeoutException {
        // 创建连接
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 配置
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/message");
        connectionFactory.setUsername("message");
        connectionFactory.setPassword("123456");
        return connectionFactory.newConnection();
    }

}
