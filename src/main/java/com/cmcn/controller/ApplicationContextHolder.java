package com.cmcn.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ranx
 * @create 2018-11-06 21:58
 **/
public class ApplicationContextHolder {
    private static ApplicationContext context;
    public static synchronized ApplicationContext getContext() {
        if (context == null) {
            new ClassPathXmlApplicationContext("application-context.xml");
        }
        return context;
    }
}
