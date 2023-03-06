package com.thuthi.kakaobotspringserver.commandHandler;

import com.thuthi.kakaobotspringserver.domain.ChatData;
import com.thuthi.kakaobotspringserver.domain.result.ResultMessage;
import com.thuthi.kakaobotspringserver.domain.result.ResultStatus;
import java.util.Optional;

public class ExitCommandHandler extends CommandHandler {

    public ExitCommandHandler() {
        super(Optional.empty());
    }

    @Override
    ResultMessage process(ChatData chatData) {
        return new ResultMessage(ResultStatus.EXIT, null);
    }
}
