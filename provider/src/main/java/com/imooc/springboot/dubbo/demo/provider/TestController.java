package com.imooc.springboot.dubbo.demo.provider;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by Administrator on 2016/11/7.
 */
@RestController
@RequestMapping(value = "/rest")
public class TestController {

    /**
     * get method
     *
     * @return
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "sdfa";
    }

}
