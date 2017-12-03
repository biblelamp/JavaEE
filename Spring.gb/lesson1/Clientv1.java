/**
 * JavaEE. Spring, lesson 1. Example with camera and camera roll
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Dec 02, 2017
 */
class Clientv1 {

    public static void main(String[] args) {
        CameraRoll cameraRoll = new CameraRoll();
        Camera camera = new Camera(cameraRoll);
        camera.takePhoto();
    }
}

class Camera {
    private CameraRoll cameraRoll;

    // dependency injection via constructor
    Camera(CameraRoll cameraRoll) {
        this.cameraRoll = cameraRoll;
    }

    void takePhoto() {
        System.out.println("Click!");
        cameraRoll.processing();
    }
}

class CameraRoll {
    void processing() {
        System.out.println("+1 pictuire");
    }
}