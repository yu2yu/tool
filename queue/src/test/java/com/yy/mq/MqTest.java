package com.yy.mq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @author: yy
 * @date: 2021/3/12
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MqTest {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void testSendByHelloWorld(){
        rabbitTemplate.convertAndSend("logs","hahaha");
    }

}
