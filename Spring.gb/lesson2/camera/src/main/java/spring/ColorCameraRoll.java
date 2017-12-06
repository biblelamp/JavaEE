package spring;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import spring.annotation.UnproducableCameraRoll;

public class ColorCameraRoll implements CameraRoll {
    public void processing() {
        System.out.println("+1 color picture");
    }
}