package com.thuthi.kakaobotspringserver.commandHandler;

import com.thuthi.kakaobotspringserver.domain.ChatData;
import com.thuthi.kakaobotspringserver.domain.Command;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;

public class CommandHandlerMapper {
    private final HashMap<Command, Function<ChatData, Optional<String>>> eventMapper = new HashMap<>();

    public CommandHandlerMapper() {
        eventMapper.put(Command.HELLO, (chatData) -> Optional.of("hello"));
        addCommandHandler(Command.ECHO, new EchoCommandHandler());
    }

    public Optional<String> process(Command command, ChatData chatData) {
        if (!eventMapper.containsKey(command)) {
            return Optional.of("[Error] Invalid Command");
        }

        return eventMapper.get(command).apply(chatData);
    }

    public boolean addCommandHandler(Command command, CommandHandler commandHandler) {
        if (eventMapper.get(command) != null) {
            return false;
        }
        eventMapper.put(command, commandHandler::handle);

        return true;
    }
}
