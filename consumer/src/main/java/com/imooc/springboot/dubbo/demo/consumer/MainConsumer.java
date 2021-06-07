package com.imooc.springboot.dubbo.demo.consumer;


import com.imooc.springboot.dubbo.demo.DemoService;
import com.taobao.stresstester.StressTestUtils;
import com.taobao.stresstester.core.StressTask;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class MainConsumer {

    public static void main(String[] args) throws Exception {
        try {
            Map<String, String> m = System.getenv();
            System.out.println("======envenv======");
            m.forEach((k, v) -> {
                System.out.println(k + "===" + v);
            });
            System.out.println("======envenv======");
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:consumer.xml");
            try {
                context.start();
                String concurrencyLevel = System.getProperty("concurrencyLevel");
                String totalRequests = System.getProperty("totalRequests");
                DemoService testService = (DemoService) context.getBean("testService");
                String r = testService.sayHello("dfa");
                System.out.println("第一次请求,返回了" + r);
                System.out.println("=======开始压测=======");

                StressTestUtils.testAndPrint(Integer.parseInt(concurrencyLevel), Integer.parseInt(totalRequests), new StressTask() {
                    public Object doTask() throws Exception {
                        String result = testService.sayHello("123123");
                        return null;
                    }
                });
                System.out.println("=======结束压测=======");
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

}