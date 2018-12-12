package com.cmcn.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/** 消费者 接收direct消息，exchange为exchangeTest,rout-key为queueTestKey
 * 测试一个exchange可以发送多个消息
 * @author ranx
 * @create 2018-11-19 21:00
 **/
public class ChrisConsumer implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(ChrisConsumer.class);

    @Override
    public void onMessage(Message message) {
        logger.info("ChrisConsumer receive message ------>:{}", message);
    }
}
