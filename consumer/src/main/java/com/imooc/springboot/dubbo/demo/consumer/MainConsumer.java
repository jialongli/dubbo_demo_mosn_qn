package com.imooc.springboot.dubbo.demo.consumer;


import com.imooc.springboot.dubbo.demo.DemoService;
import com.imooc.springboot.dubbo.demo.consumer.socket.MyHttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.taobao.stresstester.StressTestUtils;
import com.taobao.stresstester.core.StressTask;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

public class MainConsumer {
    public static void main(String[] args) throws Exception {
        try {
            Thread.sleep(5000);
            Map<String, String> m = System.getenv();
            System.out.println("======envenv======");
            m.forEach((k, v) -> {
                System.out.println(k + "===" + v);
            });
            System.out.println("======envenv======");
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:consumer.xml");
            try {
                context.start();
                DemoService testService = (DemoService) context.getBean("testService");
                startHttpServer(testService);
                String r = testService.sayHello("dfa");
                System.out.println("第一次请求,返回了" + r);

            } finally {
                context.stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        CountDownLatch a = new CountDownLatch(1);
        a.await();
        System.out.println("~~~~~~~~~~~~~~");
    }

    public static void startHttpServer(DemoService testService){
        HttpServer httpServer = null;
        try {
            httpServer = HttpServer.create(new InetSocketAddress(8088), 0);
            //创建一个HttpContext，将路径为/myserver请求映射到MyHttpHandler处理器
            httpServer.createContext("/yace", new MyHttpHandler(testService));

            //设置服务器的线程池对象
            httpServer.setExecutor(Executors.newFixedThreadPool(10));

            //启动服务器
            httpServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}