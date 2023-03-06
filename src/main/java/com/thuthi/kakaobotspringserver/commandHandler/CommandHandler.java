package com.thuthi.kakaobotspringserver.commandHandler;

import com.thuthi.kakaobotspringserver.domain.ChatData;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public abstract class CommandHandler {
    private final Optional<TargetFilter> targetFilter;

    final public Optional<String> handle(ChatData chatdata) {
        if (!targetFilter.map(tf -> tf.filter(chatdata.getRoom(), chatdata.getSender())).orElse(false)) {
            return Optional.empty();
        }
        return Optional.of(process(chatdata));
    }

    abstract String process(ChatData chatData);
}
