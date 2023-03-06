package com.thuthi.kakaobotspringserver.commandHandler;

import com.thuthi.kakaobotspringserver.domain.ChatData;
import com.thuthi.kakaobotspringserver.domain.Command;

import com.thuthi.kakaobotspringserver.domain.result.ResultMessage;
import com.thuthi.kakaobotspringserver.domain.result.ResultStatus;
import java.util.HashMap;
import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class CommandHandlerMapper {
    private final HashMap<Command, Function<ChatData, ResultMessage>> eventMapper = new HashMap<>();

    public CommandHandlerMapper() {
        eventMapper.put(Command.HELLO, (chatData) -> new ResultMessage(ResultStatus.SUCCESS, "hello"));
        addCommandHandler(Command.ECHO, new EchoCommandHandler());
        addCommandHandler(Command.EXIT, new ExitCommandHandler());
        addCommandHandler(Command.MESSAGE, new PpomppuCommandHandler());
    }

    public boolean addCommandHandler(Command command, CommandHandler commandHandler) {
        if (eventMapper.get(command) != null) {
            return false;
        }
        eventMapper.put(command, commandHandler::handle);

        return true;
    }

    public ResultMessage process(Command command, ChatData chatData) {
        if (!eventMapper.containsKey(command)) {
            return new ResultMessage(ResultStatus.FAIL, "[Error] Invalid Command");
        }

        return eventMapper.get(command).apply(chatData);
    }
}
