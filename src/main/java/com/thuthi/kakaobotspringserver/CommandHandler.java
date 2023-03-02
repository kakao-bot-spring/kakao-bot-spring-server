package com.thuthi.kakaobotspringserver;

import com.thuthi.kakaobotspringserver.domain.ChatData;
import com.thuthi.kakaobotspringserver.domain.Command;

import java.util.HashMap;
import java.util.function.Function;

public class CommandHandler {
    private static final HashMap<Command, Function<ChatData, String>> eventMapper = new HashMap<>() {{
        eventMapper.put(Command.HELLO, (chatData) -> "hello");
        eventMapper.put(Command.MESSAGE, ChatData::toString);
    }};
    public static String handle(Command command, ChatData chatData) {
        if (!eventMapper.containsKey(command)) {
            return "[Error]Invalid Command";
        }

        return eventMapper.get(command).apply(chatData);
    }
}
