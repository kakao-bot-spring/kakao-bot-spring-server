package com.thuthi.kakaobotspringserver.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thuthi.kakaobotspringserver.commandHandler.CommandHandlerMapper;
import com.thuthi.kakaobotspringserver.api.dto.KakaoRequestDto;
import com.thuthi.kakaobotspringserver.domain.ChatData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HelloApiController {
    private final CommandHandlerMapper commandHandlerMapper;
    @PostMapping("/")
    public String hello(@RequestBody KakaoRequestDto kakaoRequestDto) {
        ObjectMapper objectMapper = new ObjectMapper();
        ChatData chatData = objectMapper.convertValue(kakaoRequestDto.getData(), ChatData.class);
        return commandHandlerMapper.process(kakaoRequestDto.getCommand(), chatData).get();
    }
}
