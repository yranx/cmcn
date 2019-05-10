package com.cmcn.controller;

import com.cmcn.mq.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @author ranx
 * @create 2018-11-21 15:03
 **/
@Controller
public class RabbitMQController {

    @Autowired
    private MessageProducer messageProducer;
    @RequestMapping("/sendRabbitMessage")
    @ResponseBody
    public  void  sendRabbitMessage(){
        int a = 100;
        while (a > 0) {
            try {
                messageProducer.sendMessage("Hello, I am amq sender num :" + a--);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                //暂停一下，好让消息消费者去取消息打印出来
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
