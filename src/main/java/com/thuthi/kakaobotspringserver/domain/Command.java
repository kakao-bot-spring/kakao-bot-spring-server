package com.thuthi.kakaobotspringserver.domain;

public enum Command {
    MESSAGE("message"),
    HELLO("hello")
    ;

    private final String value;

    Command(String value) {
        this.value = value;
    }
}
