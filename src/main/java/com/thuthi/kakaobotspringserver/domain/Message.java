package com.thuthi.kakaobotspringserver.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@RequiredArgsConstructor
@Getter
@ToString
public class Message implements Serializable {
    final private String room;
    final private String msg;
    final private String sender;
    final private Boolean isGroupChat;
    final private String replier;
    final private String packageName;
}
