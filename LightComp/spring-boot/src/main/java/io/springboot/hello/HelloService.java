package io.springboot.hello;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Service;

@Service
public class HelloService implements SmartLifecycle {

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
