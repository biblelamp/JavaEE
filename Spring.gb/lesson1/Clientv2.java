/**
 * JavaEE. Spring, lesson 1. Example with camera and camera roll
 *
 * @author Sergey Iryupin
 * @version 0.2 dated Dec 03, 2017
 */
class Clientv2 {

    public static void main(String[] args) {
        Camera camera = new Camera();
        CameraRoll colorCameraRoll = new ColorCameraRoll();
        camera.setCameraRoll(colorCameraRoll);
        camera.takePhoto();
    }
}

class Camera {
    private CameraRoll cameraRoll;

    void setCameraRoll(CameraRoll cameraRoll) {
        this.cameraRoll = cameraRoll;
    }

    CameraRoll getCameraRoll() {
        return cameraRoll;
    }

    void takePhoto() {
        System.out.println("Click!");
        cameraRoll.processing();
    }
}

class ColorCameraRoll implements CameraRoll {
    public void processing() {
        System.out.println("+1 color pictuire");
    }
}

class BlackAndWhiteCameraRoll implements CameraRoll {
    public void processing() {
        System.out.println("+1 b/w pictuire");
    }
}

interface CameraRoll {
    void processing();
}