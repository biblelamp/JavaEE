class Client {

    public static void main(String[] args) {
        Camera camera = new Camera();
        camera.setCameraRoll(new CameraRoll());
        camera.doPhotograph();
    }
}