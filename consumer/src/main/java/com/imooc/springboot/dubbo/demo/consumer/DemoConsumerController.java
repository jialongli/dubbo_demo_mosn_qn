package com.imooc.springboot.dubbo.demo.consumer;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.imooc.springboot.dubbo.demo.DemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoConsumerController {
    //    @Reference(url = "dubbo://172.17.0.2:20880")
//    @Reference(url = "dubbo://localhost:2045")
    @Reference(url = "dubbo://localhost:2045",group = "testGroup", version = "12")
    private DemoService demoService;

    @RequestMapping("/sayHello")
    public String sayHello(@RequestParam String name) {
        String a = "aaaa";
        return demoService.sayHello(name);
    }

    @RequestMapping("/decode")
    public String decode(@RequestParam("url") String name) {
        return URL.decode(name);
    }


    @RequestMapping("/test")
    public String test(@RequestParam String name) {
        String a = "aaaa";
        return "aaa";
    }

    public static void main(String[] args) {
        System.out.println("a".getBytes().length);
    }

}