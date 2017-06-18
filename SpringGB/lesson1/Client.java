class Client {

    public static void main(String[] args) {
        Camera camera = new Camera();
        CameraRoll cameraRoll = new ColorCameraRoll();
        camera.setCameraRoll(cameraRoll);
        camera.doPhotograph();
        /* added idea of the assistant
        Assistant assistant = new Assistant();
        Camera camera = assistant.getCamera();
        camera.doPhotograph();
        */
    }
}