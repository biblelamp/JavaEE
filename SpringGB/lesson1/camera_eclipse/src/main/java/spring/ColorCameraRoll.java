package spring;

import org.springframework.stereotype.Component;

@Component("cameraRoll")
public class ColorCameraRoll implements CameraRoll {

    public void processing() {
        System.out.println("-1 color photo");
    }
}