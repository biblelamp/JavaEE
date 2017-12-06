package spring;

import org.springframework.stereotype.Component;

import spring.annotation.UnproducableCameraRoll;

@Component("cameraRoll")
@UnproducableCameraRoll(usingCameraRollClass = ColorCameraRoll.class)
public class BlackAndWhiteCameraRoll implements CameraRoll {
    public void processing(){
        System.out.println("+1 b/w picture");
    }
}