package com.cmcn.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 消息生产者
 * @author ranx
 * @create 2018-11-19 20:53
 **/
@Service
public class MessageProducer {
    private Logger logger = LoggerFactory.getLogger(MessageProducer.class);

    @Resource(name = "amqpTemplate")
//    @Autowired
    private AmqpTemplate amqpTemplate;


    public void sendMessage(Object message) throws IOException {
        logger.info("send message:{}", message);
        amqpTemplate.convertAndSend("queueTestKey", message);
        amqpTemplate.convertAndSend("queueTestChris", message);
    }
}
