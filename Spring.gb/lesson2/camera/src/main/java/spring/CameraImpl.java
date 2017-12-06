package spring;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("camera")
public class CameraImpl implements Camera {

    @Autowired
    private CameraRoll cameraRoll;

    @Value("false")
    private boolean broken;

    public CameraRoll getCameraRoll() {
        return cameraRoll;
    }

    public void setCameraRoll(CameraRoll cameraRoll) {
        this.cameraRoll = cameraRoll;
    }

    public boolean isBroken() {
        return broken;
    }

    public void breaking() {
        this.broken = true;
    }

    public void makePhoto() {
        if (isBroken()) {
            System.out.println("Camera is broken!");
            return;
        }
        System.out.println("Photo taken successfully.");
        cameraRoll.processing();
    }

    @PostConstruct
    public void ready() {
        System.out.println("Camera is ready to use.");
    }
}