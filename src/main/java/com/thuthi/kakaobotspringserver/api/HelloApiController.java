package com.thuthi.kakaobotspringserver.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thuthi.kakaobotspringserver.CommandHandler;
import com.thuthi.kakaobotspringserver.api.dto.KakaoRequestDto;
import com.thuthi.kakaobotspringserver.domain.ChatData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.apache.logging.log4j.message.ParameterizedMessage.deepToString;

@RestController
public class HelloApiController {
    @PostMapping("/")
    public String hello(@RequestBody KakaoRequestDto kakaoRequestDto) throws JsonProcessingException {
        System.out.println("HelloApiController.hello");
        System.out.println("kakaoRequestDto = " + deepToString(kakaoRequestDto));
        ChatData chatData = (new ObjectMapper()).readValue(kakaoRequestDto.getData().toString(), ChatData.class);
        return CommandHandler.handle(kakaoRequestDto.getCommand(), chatData);
    }
}
