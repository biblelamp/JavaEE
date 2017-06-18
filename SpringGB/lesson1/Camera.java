class Camera {
    private CameraRoll cameraRoll = new CameraRoll();

    public void doPhotograph(){
        System.out.println("Click!");
        cameraRoll.processing();
    }
}