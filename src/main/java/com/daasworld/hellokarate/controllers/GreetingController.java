package com.daasworld.hellokarate.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    // $ curl localhost:8080/api/hello
    @GetMapping(value = "/api/hello", produces = "application/txt")
    public String greet(@RequestParam(required=false, defaultValue = "world") String name) {
        return String.format("Hello %s!", name);
    }
}
