package com.thuthi.kakaobotspringserver.socket;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

@Log4j2
@RequiredArgsConstructor
public class SocketHandler extends Thread {
    private final Socket socket;
    @Override
    public void run() {
        try (DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));) {
            while (true) {
                String line;
                while(!socket.isClosed() && (line = in.readLine()) != null) {
                    line += '\n';
                    dout.write(line.getBytes(StandardCharsets.UTF_8));
                    dout.flush();
                    log.info("[SOCKET] message: " + line);
                }
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
