class Client {

    public static void main(String[] args) {
        Camera camera = new Camera();
        CameraRoll cameraRoll = new ColorCameraRoll();
        camera.setCameraRoll(cameraRoll);
        camera.doPhotograph();
    }
}