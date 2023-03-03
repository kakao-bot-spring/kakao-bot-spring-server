package com.thuthi.kakaobotspringserver.commandHandler;

import com.thuthi.kakaobotspringserver.domain.ChatData;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public abstract class CommandHandler {
    private final Optional<String> targetRoom;
    private final Optional<String> targetSender;

    final public Optional<String> handle(ChatData chatdata) {
        if (!isTargetRoom(chatdata.getRoom())) {
            return Optional.empty();
        }
        if (!isTargetSender(chatdata.getSender())) {
            return Optional.empty();
        }
        return Optional.of(process(chatdata));
    }

    abstract String process(ChatData chatData);

    private boolean isTargetRoom(String room) {
        return targetRoom.map(room::equals).orElse(true);
    }

    private boolean isTargetSender(String sender) {
        return targetSender.map(sender::equals).orElse(true);
    }
}
