package com.imooc.springboot.dubbo.demo.provider;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class TestController {

    @RequestMapping
    public String test() {
        return "fads";
    }

}
