package com.thuthi.kakaobotspringserver.commandHandler;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
final public class TargetFilter {
    private final String targetRoom;
    private final String targetSender;

    public boolean filter(String room, String sender) {
        return targetRoom.equals(room) && targetSender.equals(sender);
    }
}
