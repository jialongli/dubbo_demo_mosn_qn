package com.imooc.springboot.dubbo.demo.consumer;


import com.imooc.springboot.dubbo.demo.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainConsumer {

    public static void main(String[] args) throws Exception {
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:consumer.xml");
            try {
                context.start();
                DemoService testService = (DemoService) context.getBean("testService");
                while (true) {
                    try {
                        System.out.println(testService.sayHello("fdasfs"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.in.read();
                }
            } finally {
                context.stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("~~~~~~~~~~~~~~");
    }

}