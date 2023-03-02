package com.thuthi.kakaobotspringserver.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ChatData {
    private String room;
    private String msg;
    private String sender;
    private Boolean isGroupChat;
    private String replier;
    private String packageName;
}
