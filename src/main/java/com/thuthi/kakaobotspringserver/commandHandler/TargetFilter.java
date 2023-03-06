package com.thuthi.kakaobotspringserver.commandHandler;

final public class TargetFilter {
    private String targetRoom;
    private String targetSender;

    public boolean filter(String room, String sender) {
        return targetRoom.equals(room) && targetSender.equals(sender);
    }
}
