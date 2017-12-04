package spring;

import org.springframework.stereotype.Component;

@Component("bullets")
public class ExplosiveBullets implements Bullets {

    public void processing() {
        System.out.println("1 shot with explosive bullet");
    }
}