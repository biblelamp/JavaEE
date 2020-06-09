package io.springboot.hello;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class HelloComponent {

    @PostConstruct
    public void init() {
        System.out.println("Hello, world!");
    }
}
