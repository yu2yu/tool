package com.yy.mq.fanout;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Envelope;
import com.yy.mq.util.MqUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @description:
 * @author: yy
 * @date: 2021/3/11
 */
public class Consumer {

    public static void main(String[] args) throws InterruptedException {

        FanoutConsumer consumer1 = new FanoutConsumer("消费者1");
        FanoutConsumer consumer2 = new FanoutConsumer("消费者2");
        new Thread(consumer1).start();
        new Thread(consumer2).start();

        TimeUnit.SECONDS.sleep(200);
    }
}
class FanoutConsumer implements Runnable{

    String str;

    public FanoutConsumer(String str){
        this.str = str;
    }

    @Override
    public void run() {
        try{
            Connection connection = MqUtil.getConnection();
            Channel channel = connection.createChannel();
            // 创建临时队列
            String queue = channel.queueDeclare().getQueue();
            channel.queueBind(queue,"orders","");

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