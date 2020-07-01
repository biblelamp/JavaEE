package io.springboot.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.SmartLifecycle;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@Service
public class HelloService implements SmartLifecycle, Runnable {

    static final Logger log = LoggerFactory.getLogger(HelloService.class);

    private final String HELLO = "hello.txt";

    private String helloMsg;

    @Scheduled(fixedRate = 5000) // 5000 ms = 5 s
    public void sheduled() {
        log.info("Sheduled: {}", helloMsg);
    }

    public void run() {
        while (true) {
            log.info("Thread run: {}", helloMsg);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void start() {
        try {
            helloMsg = StreamUtils.copyToString(new ClassPathResource(HELLO)
                    .getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("SmartLifecycle: {}", helloMsg);
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
