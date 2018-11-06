package com.cmcn.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ranx
 * @create 2018-11-06 21:58
 **/
public class ApplicationContexxtHolder {
    public static void main(String[] args) {
        //创建Spring的ioc容器对象
        ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
        //从容器中获取bean实例
        ServerStartMain serverStartMain = (ServerStartMain) ctx.getBean("helloNetty");
        serverStartMain.hello();
    }
}
