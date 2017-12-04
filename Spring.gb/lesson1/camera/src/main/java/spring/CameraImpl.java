package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("camera")
public class CameraImpl implements Camera {

    @Autowired
    @Qualifier("cameraRoll")
    private CameraRoll cameraRoll;

    @Override
    public CameraRoll getCameraRoll() {
        return cameraRoll;
    }

    @Override
    public void setCameraRoll(CameraRoll cameraRoll) {
        this.cameraRoll = cameraRoll;
    }

    @Override
    public void makePhoto(){
        System.out.println("Click!");
        cameraRoll.processing();
    }
}