package io.springboot.hello;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class HelloComponent {

    public HelloComponent() {
        System.out.println("Constructor: Hello, world!");
    }

    @PostConstruct
    public void init() {
        System.out.println("PostConstruct: Hello, world!");
    }

}
