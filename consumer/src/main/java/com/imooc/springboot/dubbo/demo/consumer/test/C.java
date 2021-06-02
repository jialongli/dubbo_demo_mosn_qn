package com.imooc.springboot.dubbo.demo.consumer.test;

public class C implements Parent,Parent2{
    @Override
    public void a() {
        System.out.printf("asdfas");
    }

    public static void main(String[] args) {
        C c=new C();
        if(c instanceof Parent){
            System.out.println("ins P");
        }
        if(c instanceof Parent2){
            System.out.println("ins P2");
        }
    }

    @Override
    public void b() {

    }
}
