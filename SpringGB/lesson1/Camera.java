class Camera {
    private CameraRoll cameraRoll;

    Camera(CameraRoll cameraRoll) {
        this.cameraRoll = cameraRoll;
    }

    public void doPhotograph(){
        System.out.println("Click!");
        cameraRoll.processing();
    }
}