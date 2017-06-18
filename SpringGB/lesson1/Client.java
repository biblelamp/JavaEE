class Client {

    public static void main(String[] args) {
        Camera camera = new Camera(new CameraRoll());
        camera.doPhotograph();
    }
}