package com.thuthi.kakaobotspringserver.commandHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thuthi.kakaobotspringserver.domain.ChatData;

import com.thuthi.kakaobotspringserver.domain.result.ResultMessage;
import com.thuthi.kakaobotspringserver.domain.result.ResultStatus;
import java.util.Arrays;
import java.util.Optional;

public class EchoCommandHandler extends CommandHandler {
    public EchoCommandHandler() {
        super(Optional.empty());
    }

    @Override
    ResultMessage process(ChatData chatData) {
        try {
            String message = (new ObjectMapper()).writeValueAsString(chatData);
            return new ResultMessage(ResultStatus.SUCCESS, message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResultMessage(ResultStatus.FAIL, Arrays.toString(e.getStackTrace()));
        }
    }
}
