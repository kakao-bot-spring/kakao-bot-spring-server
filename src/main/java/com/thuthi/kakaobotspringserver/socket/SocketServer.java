package com.thuthi.kakaobotspringserver.socket;

import com.thuthi.kakaobotspringserver.commandHandler.CommandHandlerMapper;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.ServerSocket;
import java.net.Socket;

@Log4j2
@Component
@RequiredArgsConstructor
public class SocketServer {
    @Value("${socket.port}")
    public int PORT;
    private final CommandHandlerMapper commandHandlerMapper;
    public void start() {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(PORT)){
                log.info("[SOCKET]소켓 동작중... port: " + PORT);
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    log.info("[SOCKET]client 연결 성공");
                    new SocketMessageHandler(clientSocket, commandHandlerMapper).start();
                }
            } catch (Exception e) {
                log.error(e.getMessage());
                log.error(Arrays.toString(e.getStackTrace()));
            }
        }).start();

    }
}
