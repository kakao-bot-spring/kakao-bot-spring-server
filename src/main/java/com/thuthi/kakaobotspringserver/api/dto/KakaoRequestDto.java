package com.thuthi.kakaobotspringserver.api.dto;

import com.thuthi.kakaobotspringserver.domain.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;

@AllArgsConstructor
@Getter
public class KakaoRequestDto {
    final private Command command;
    final private HashMap<String, String> data;
}
