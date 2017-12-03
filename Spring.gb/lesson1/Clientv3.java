/**
 * JavaEE. Spring, lesson 1. Example with camera and camera roll
 *
 * @author Sergey Iryupin
 * @version 0.3 dated Dec 03, 2017
 */
class Clientv3 {

    public static void main(String[] args) {
        Assistant assistant = new Assistant();
        Camera camera = assistant.getCamera();
        camera.takePhoto();
    }
}

class Assistant {
    Camera getCamera() {
        Camera camera = new Camera();
        CameraRoll colorCameraRoll = new ColorCameraRoll();
        camera.setCameraRoll(colorCameraRoll);
        return camera;
    }
}