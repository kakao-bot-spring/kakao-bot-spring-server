package com.thuthi.kakaobotspringserver.commandHandler;

import com.thuthi.kakaobotspringserver.domain.ChatData;
import com.thuthi.kakaobotspringserver.domain.result.ResultMessage;
import com.thuthi.kakaobotspringserver.domain.result.ResultStatus;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public abstract class CommandHandler {
    private final Optional<TargetFilter> targetFilter;
    private final String prefix;

    final public ResultMessage handle(ChatData chatdata) {
        if (!chatdata.getMsg().startsWith(prefix)) {
            return new ResultMessage(ResultStatus.FILTERED, null);
        }
        if (!targetFilter.map(tf -> tf.filter(chatdata.getRoom(), chatdata.getSender())).orElse(false)) {
            return new ResultMessage(ResultStatus.FILTERED, null);
        }
        return process(chatdata);
    }

    abstract ResultMessage process(ChatData chatData);
}
