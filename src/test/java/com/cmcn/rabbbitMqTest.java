package com.cmcn;

import com.cmcn.controller.ApplicationContextHolder;
import com.cmcn.mq.MessageProducer;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ranx
 * @create 2018-11-19 21:25
 **/
public class rabbbitMqTest {
    private Logger logger = LoggerFactory.getLogger(rabbbitMqTest.class);

    @Test
    public void should_send_a_amq_message() throws Exception {
//        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context-rabbitmq.xml");
//        MessageProducer messageProducer = (MessageProducer) context.getBean("messageProducer");
        MessageProducer messageProducer = new MessageProducer();
        int a = 100;
        while (a > 0) {
            messageProducer.sendMessage("Hello, I am amq sender num :" + a--);
            try {
                //暂停一下，好让消息消费者去取消息打印出来
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
