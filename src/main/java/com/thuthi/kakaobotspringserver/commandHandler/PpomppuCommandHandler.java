package com.thuthi.kakaobotspringserver.commandHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thuthi.kakaobotspringserver.domain.ChatData;
import com.thuthi.kakaobotspringserver.domain.result.ResultMessage;
import com.thuthi.kakaobotspringserver.domain.result.ResultStatus;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

public class PpomppuCommandHandler extends CommandHandler {
    public PpomppuCommandHandler() {
        super(Optional.of(new TargetFilter("조병우", "조병우")), "/");
    }

    @Override
    ResultMessage process(ChatData chatData) {
        String[] splits = chatData.getMsg().split(" ");
        switch (splits[0]) {
            case "/등록" -> {
                if (splits.length != 2) {
                    return new ResultMessage(ResultStatus.FAIL, "[사용법]\n/등록 {단어}");
                }
                return registerWord(splits[1]);
            }
            case "/삭제" -> {
                if (splits.length != 2) {
                    return new ResultMessage(ResultStatus.FAIL, "[사용법]\n/삭제 {단어}");
                }
                return deleteWord(splits[1]);
            }
        }
        return null;
    }

    private ResultMessage registerWord(String word) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File("./config.json");

            HashSet<String> readValue = new HashSet<String>(
                    objectMapper.readValue(file, ArrayList.class));

            readValue.add(word);
            objectMapper.writeValue(file, readValue);
            return new ResultMessage(ResultStatus.SUCCESS, "[현재 등록된 명령어]\n" + readValue);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultMessage(ResultStatus.FAIL, "알 수 없는 에러가 발생했습니다.");
        }
    }

    private ResultMessage deleteWord(String word) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File("./config.json");

            HashSet<String> readValue = new HashSet<String>(
                    objectMapper.readValue(file, ArrayList.class));

            readValue.remove(word);
            objectMapper.writeValue(file, readValue);
            return new ResultMessage(ResultStatus.SUCCESS, "[현재 등록된 명령어]\n" + readValue);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultMessage(ResultStatus.FAIL, "알 수 없는 에러가 발생했습니다.");
        }
    }
}
