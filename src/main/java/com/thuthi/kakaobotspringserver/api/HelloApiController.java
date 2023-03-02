package com.thuthi.kakaobotspringserver.api;

import com.thuthi.kakaobotspringserver.domain.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloApiController {
    @PostMapping("/")
    public String hello(@RequestBody Message message) {
        System.out.println("HelloApiController.hello");
        System.out.println("message = " + message.toString());
        return "hello";
    }
}
