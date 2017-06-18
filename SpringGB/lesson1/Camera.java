class Camera {
    private CameraRoll cameraRoll;

    public void doPhotograph(){
        System.out.println("Click!");
        cameraRoll.processing();
    }
}