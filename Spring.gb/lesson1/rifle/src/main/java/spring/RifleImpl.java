package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("rifle")
public class RifleImpl implements Rifle {

    @Autowired
    @Qualifier("bullets")
    private Bullets bullets;

    public Bullets getBullets()  {
        return bullets;
    }

    public void setBullets(Bullets bullets) {
        this.bullets = bullets;
    }

    public void shot(){
        System.out.println("Shot!");
        bullets.processing();
    }
}