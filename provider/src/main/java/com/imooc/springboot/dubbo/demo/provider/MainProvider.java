package com.imooc.springboot.dubbo.demo.provider;


import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainProvider {


    public static void main(String[] args) throws Exception {

        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:provider.xml");
            try {
                System.out.println("helloooooooo");
                context.start();
                System.in.read();
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