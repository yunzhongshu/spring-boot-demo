package com.zmdj.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangyunyun
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    private Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/v1")
    public String helloWorld() {

        logger.info("This is a INFO log.");
        logger.warn("This is a WARN log.");
        logger.error("This is a ERROR log.");
        return "Hello world!";
    }

}
