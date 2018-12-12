package com.cmcn.controller;

/**
 * @author ranx
 * @create 2018-11-21 13:57
 **/
public class Hello {
    private String name;
    public void setName(String name) {
        System.out.println("调用了设置属性:" + name);
        this.name = name;
    }
    public Hello(){
        System.out.println("初始化构造器");
    }
    public void say(){
        System.out.println("Hello: " + name);
    }
}
