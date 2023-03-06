package com.thuthi.kakaobotspringserver.commandHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thuthi.kakaobotspringserver.domain.ChatData;

import java.util.Optional;

public class EchoCommandHandler extends CommandHandler {
    public EchoCommandHandler() {
        super(Optional.empty());
    }

    @Override
    String process(ChatData chatData) {
        try {
            return (new ObjectMapper()).writeValueAsString(chatData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
