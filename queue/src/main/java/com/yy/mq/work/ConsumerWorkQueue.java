package com.yy.mq.work;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.yy.mq.util.MqUtil;
import lombok.SneakyThrows;

import java.io.IOException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: yy
 * @date: 2021/3/10
 */
public class ConsumerWorkQueue {

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Consumer()).start();
        new Thread(new Consumer2()).start();

        TimeUnit.SECONDS.sleep(100);
    }

}

class Consumer implements Runnable{

    @SneakyThrows
    @Override
    public void run() {
        Connection connection = MqUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work", false, false, false, null);
        channel.basicQos(1);
        channel.basicConsume("work", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1：" + new String(body));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}

class Consumer2 implements Runnable{

    @SneakyThrows
    @Override
    public void run() {
        Connection connection = MqUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work", false, false, false, null);
        channel.basicQos(1);
        channel.basicConsume("work", false, new DefaultConsumer(channel) {
            @SneakyThrows
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2：" + new String(body));
                TimeUnit.SECONDS.sleep(5);
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
