package com.thuthi.kakaobotspringserver.api;

import jakarta.annotation.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloApiController {
    @GetMapping("/")
    public String hello(@Nullable @RequestBody String request) {
        System.out.println("HelloApiController.hello");
        System.out.println("request = " + request);
        return "hello";
    }
}
