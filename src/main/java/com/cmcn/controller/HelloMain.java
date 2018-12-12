package com.cmcn.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ranx
 * @create 2018-11-21 14:01
 **/
public class HelloMain {
    public static void main(String[] args) {
//        ApplicationContext ctx = ApplicationContextHolder.getContext();
        ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
        Hello hello = (Hello) ctx.getBean("helloName");
        hello.say();
    }

}
