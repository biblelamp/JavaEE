package io.springboot.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.SmartLifecycle;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class HelloService implements SmartLifecycle {

    static final Logger log = LoggerFactory.getLogger(HelloService.class);

    @Scheduled(fixedRate = 5000) // 5000 ms = 5 s
    public void sheduled() {
        log.info("Sheduled: Hello, world!");
    }

    @Override
    public void start() {
        log.info("SmartLifecycle: Hello, world!");
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
