package com.daasworld.hellokarate.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    // $ curl localhost:8080/api/hello
    @GetMapping(value = "/api/hello", produces = "application/txt")
    public String greet(@RequestParam(required=false, defaultValue = "world") String name) {
        logger.info("greet() called");
        return String.format("Hello %s!", name);
    }
}
