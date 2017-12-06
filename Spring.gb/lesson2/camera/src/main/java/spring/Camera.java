package spring;

public interface Camera {
    CameraRoll getCameraRoll();
    void setCameraRoll(CameraRoll cameraRoll);
    void makePhoto();
    void breaking();
    boolean isBroken();
    public void ready();
}