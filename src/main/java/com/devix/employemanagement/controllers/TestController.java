package com.devix.employemanagement.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testApi")
public class TestController {

    @Value("${INSTANCE_NAME}")
    private String instance;

    @GetMapping("/test")
    public String test() {
        return "Response from " + instance;
    }
}
