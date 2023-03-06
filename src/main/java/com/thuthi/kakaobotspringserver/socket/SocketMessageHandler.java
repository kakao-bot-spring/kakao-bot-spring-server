package com.thuthi.kakaobotspringserver.socket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thuthi.kakaobotspringserver.commandHandler.CommandHandlerMapper;
import com.thuthi.kakaobotspringserver.domain.ChatData;
import com.thuthi.kakaobotspringserver.domain.Command;
import com.thuthi.kakaobotspringserver.domain.result.ResultMessage;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
public class SocketMessageHandler extends Thread {
    private final Socket socket;
    private final CommandHandlerMapper commandHandlerMapper;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void run() {
        try (DataOutputStream outputStream = new DataOutputStream(
                socket.getOutputStream()); BufferedReader inputStream = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8))) {
            processIO(inputStream, outputStream);
        } catch (Exception e) {
            log.error(e);
        } finally {
            try {
                if (!socket.isClosed()) {
                    log.info("[SOCKET] disconnected: " + socket.getInetAddress().toString());
                    socket.close();
                }
            } catch (Exception e) {
                log.error(e);
            }
        }
    }

    private void processIO(BufferedReader inputStream, DataOutputStream outputStream) throws IOException {
        String inputString;
        while (!socket.isClosed() && (inputString = inputStream.readLine()) != null) {
            log.info("[SOCKET] received message: " + inputString);
            ResultMessage result = getResult(inputString);
            switch (result.resultStatus()) {
                case SUCCESS -> {
                    outputStream.write((result.message()).getBytes(StandardCharsets.UTF_8));
                    outputStream.flush();
                    log.info("[SOCKET] sended message: " + result.message());
                }
                case FILTERED -> log.info("[SOCKET] filtered");
                case FAIL -> {
                    outputStream.write((result.message()).getBytes(StandardCharsets.UTF_8));
                    outputStream.flush();
                    log.info("[SOCKET] fail by " + result.message());
                }
                case EXIT -> socket.close();
            }
        }
    }

    private ResultMessage getResult(String line) throws JsonProcessingException {
        HashMap<String, Object> hashMap = objectMapper.readValue(line, HashMap.class);
        Command command = Command.valueOf(((String) hashMap.get("command")).toUpperCase());
        ChatData chatData = objectMapper.convertValue(hashMap.get("data"), ChatData.class);
        ResultMessage resultMessage = commandHandlerMapper.process(command, chatData);
        return new ResultMessage(resultMessage.resultStatus(), String.format("{\"room\": \"%s\", \"msg\": \"%s\"}\n", chatData.getRoom(), resultMessage.message()));
    }
}
