package com.thuthi.kakaobotspringserver.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@ToString
public class ChatData {
    final private String room;
    final private String msg;
    final private String sender;
    final private Boolean isGroupChat;
    final private String replier;
    final private String packageName;
}
