package com.beauty.api.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
@Hidden
public class HomeController {
    Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping
    public String test() {
        this.logger.warn("This is working message");
        return "Testing message";
    }
}
