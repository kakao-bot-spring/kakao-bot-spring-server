package com.thuthi.kakaobotspringserver;

import com.thuthi.kakaobotspringserver.domain.ChatData;
import com.thuthi.kakaobotspringserver.domain.Command;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.function.Function;

@Component
public class CommandHandler {
    private final HashMap<Command, Function<ChatData, String>> eventMapper = new HashMap<>();

    public CommandHandler() {
        eventMapper.put(Command.HELLO, (chatData) -> "hello");
        eventMapper.put(Command.MESSAGE, ChatData::toString);
    }

    public String handle(Command command, ChatData chatData) {
        if (!eventMapper.containsKey(command)) {
            return "[Error]Invalid Command";
        }

        return eventMapper.get(command).apply(chatData);
    }
}
