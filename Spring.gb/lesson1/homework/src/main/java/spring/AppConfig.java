package spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean(name="bullets")
	public Bullets bullets() {
		return new ExplosiveBullets();
	}

	@Bean(name="rifle")
	public Rifle rifle(Bullets bullets) {
		Rifle rifle = new RifleImpl();
		rifle.setBullets(bullets);
		return rifle;
	}
}