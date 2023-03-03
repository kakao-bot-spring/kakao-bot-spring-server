package com.thuthi.kakaobotspringserver.socket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thuthi.kakaobotspringserver.commandHandler.CommandHandlerMapper;
import com.thuthi.kakaobotspringserver.domain.ChatData;
import com.thuthi.kakaobotspringserver.domain.Command;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
public class SocketMessageHandler extends Thread {
    private final Socket socket;
    private final CommandHandlerMapper commandHandlerMapper;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void run() {
        try (DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
             BufferedReader inputSream = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));) {
            String inputString;
            while(!socket.isClosed() && (inputString = inputSream.readLine()) != null) {
                log.info("[SOCKET] received message: " + inputString);
                Optional<String> result = getResult(inputString);
                if (result.isEmpty()) {
                    continue;
                }

                outputStream.write((result.get() + '\n').getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
                log.info("[SOCKET] sended message: " + result.get());
            }
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

    private Optional<String> getResult(String line) throws JsonProcessingException {
        HashMap<String, Object> hashMap = objectMapper.readValue(line, HashMap.class);
        Command command = Command.valueOf(((String)hashMap.get("command")).toUpperCase());
        ChatData chatData = objectMapper.convertValue(hashMap.get("data"), ChatData.class);
        return commandHandlerMapper.process(command, chatData);
    }
}
