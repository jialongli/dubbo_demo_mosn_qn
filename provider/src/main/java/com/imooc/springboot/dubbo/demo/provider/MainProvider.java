package com.imooc.springboot.dubbo.demo.provider;


import org.apache.dubbo.common.config.Configuration;
import org.apache.dubbo.rpc.model.ApplicationModel;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class MainProvider {


    public static void main(String[] args) throws Exception {

        try {
            Map<String, String> m = System.getenv();
            System.out.println("======envenv======");
            m.forEach((k, v) -> {
                System.out.println(k + "===" + v);
            });
            System.out.println("======envenv======");
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:provider.xml");
            try {
                Configuration configuration = ApplicationModel.getEnvironment().getConfiguration();
                System.out.println("helloooooooo");
                context.start();
                CountDownLatch a = new CountDownLatch(1);
                a.await();
            } finally {
                context.stop();
            }
        } catch (Exception e) {
            System.out.println("~~~~异常~~~~~~~~~~");

            e.printStackTrace();

            System.out.println("~~~~~~~异常~~~~~~~");

        }
        System.out.println("~~~~~~~~~~~~~~");
    }

}