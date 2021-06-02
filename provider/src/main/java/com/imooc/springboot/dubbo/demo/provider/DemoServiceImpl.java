package com.imooc.springboot.dubbo.demo.provider;

import com.imooc.springboot.dubbo.demo.DemoService;

public class DemoServiceImpl implements DemoService {

    public String sayHello(String name) {
        return "Hello, " + name + " (from Spring Boot)";
    }

}