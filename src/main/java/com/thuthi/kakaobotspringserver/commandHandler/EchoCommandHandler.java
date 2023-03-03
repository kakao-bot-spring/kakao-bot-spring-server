package com.thuthi.kakaobotspringserver.commandHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thuthi.kakaobotspringserver.domain.ChatData;
import lombok.extern.log4j.Log4j2;

import java.util.Optional;

@Log4j2
public class EchoCommandHandler extends CommandHandler {
    public EchoCommandHandler() {
        super(Optional.empty(), Optional.empty());
    }

    @Override
    String process(ChatData chatData) {
        try {
            return (new ObjectMapper()).writeValueAsString(chatData);
        } catch (JsonProcessingException e) {
            log.error(e);
            return null;
        }
    }
}
