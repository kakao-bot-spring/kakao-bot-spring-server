package com.thuthi.kakaobotspringserver.api;

import jakarta.annotation.Nullable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloApiController {
    @PostMapping("/")
    public String hello(@Nullable @RequestBody String request) {
        System.out.println("HelloApiController.hello");
        System.out.println("request = " + request);
        return "hello";
    }
}
