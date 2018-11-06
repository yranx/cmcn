package com.cmcn.controller;

/**
 * @author ranx
 * @create 2018-11-06 21:54
 **/
public class ServerStartMain {
    public String name;

    public void setName(String name) {
        this.name = name;
    }

    public void hello() {
        System.out.println("hello:" + name);
    }
}
