package io.springboot.hello;

import org.springframework.context.SmartLifecycle;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class HelloService implements SmartLifecycle {

    @Scheduled(fixedRate = 5000)
    public void sheduled() {
        System.out.printf("%tc Sheduled: Hello, world!\n", new Date());
    }

    @Override
    public void start() {
        System.out.println("SmartLifecycle: Hello, world!");
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
