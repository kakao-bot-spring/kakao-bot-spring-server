package com.thuthi.kakaobotspringserver.commandHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thuthi.kakaobotspringserver.domain.ChatData;
import com.thuthi.kakaobotspringserver.domain.Command;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;

@Component
public class CommandHandlerMapper {
    private final HashMap<Command, Function<ChatData, Optional<String>>> eventMapper = new HashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final CommandHandler echoCommandHandler = new EchoCommandHandler();

    public CommandHandlerMapper() {
        eventMapper.put(Command.HELLO, (chatData) -> Optional.of("hello"));
//        eventMapper.put(Command.MESSAGE, ChatData::toString);
        eventMapper.put(Command.ECHO, echoCommandHandler::handle);
    }

    public Optional<String> process(Command command, ChatData chatData) {
        if (!eventMapper.containsKey(command)) {
            return Optional.of("[Error] Invalid Command");
        }

        return eventMapper.get(command).apply(chatData);
    }
}
