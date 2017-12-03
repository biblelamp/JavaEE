package spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean(name="cameraRoll")
	public CameraRoll cameraRoll() {
		return new ColorCameraRoll();
	}

	@Bean(name="camera")
	public Camera camera(CameraRoll cameraRoll) {
		Camera camera = new CameraImpl();
		camera.setCameraRoll(cameraRoll);
		return camera;
	}
}