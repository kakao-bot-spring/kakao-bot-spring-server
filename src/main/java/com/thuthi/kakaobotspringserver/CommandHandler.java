package com.thuthi.kakaobotspringserver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thuthi.kakaobotspringserver.domain.ChatData;
import com.thuthi.kakaobotspringserver.domain.Command;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.function.Function;

@Component
public class CommandHandler {
    private final HashMap<Command, Function<ChatData, String>> eventMapper = new HashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CommandHandler() {
        eventMapper.put(Command.HELLO, (chatData) -> "hello");
        eventMapper.put(Command.MESSAGE, ChatData::toString);
        eventMapper.put(Command.ECHO, (chatData) -> {
            try {
                return objectMapper.writeValueAsString(chatData);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public String handle(Command command, ChatData chatData) {
        if (!eventMapper.containsKey(command)) {
            return "[Error]Invalid Command";
        }

        return eventMapper.get(command).apply(chatData);
    }
}
