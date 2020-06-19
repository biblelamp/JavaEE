package io.springboot.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.SmartLifecycle;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class HelloService implements SmartLifecycle, Runnable {

    static final Logger log = LoggerFactory.getLogger(HelloService.class);

    @Scheduled(fixedRate = 5000) // 5000 ms = 5 s
    public void sheduled() {
        log.info("Sheduled: Hello, world!");
    }

    public void run() {
        while (true) {
            log.info("Thread run: Hello, world!");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void start() {
        log.info("SmartLifecycle: Hello, world!");
        new Thread(() -> {
            run();
        }).start();
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
