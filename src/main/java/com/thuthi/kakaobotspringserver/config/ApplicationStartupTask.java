package com.thuthi.kakaobotspringserver.config;

import com.thuthi.kakaobotspringserver.socket.SocketServer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ApplicationStartupTask implements ApplicationListener<ApplicationReadyEvent> {
    private final SocketServer socketServer;
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        socketServer.start();
    }
}
