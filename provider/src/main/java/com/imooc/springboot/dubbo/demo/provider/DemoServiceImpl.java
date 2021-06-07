package com.imooc.springboot.dubbo.demo.provider;

import com.imooc.springboot.dubbo.demo.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoServiceImpl implements DemoService {

    private static final Logger LOG = LoggerFactory.getLogger(DemoServiceImpl.class);


    public String sayHello(String name) {
        LOG.info("hell========server====");
        return "Hello, " + name + " (from Spring Boot)";
    }

}