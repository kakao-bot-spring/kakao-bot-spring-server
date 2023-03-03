package com.thuthi.kakaobotspringserver.socket;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

@Log4j2
@RequiredArgsConstructor
public class SocketMessageHandler extends Thread {
    private final Socket socket;
    @Override
    public void run() {
        try (DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
             BufferedReader inputSream = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));) {
            String line;
            while(!socket.isClosed() && (line = inputSream.readLine()) != null) {
                log.info("[SOCKET] received message: " + line);
                line += '\n';
                outputStream.write(line.getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
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
}
