package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.context.annotation.*;

class Client {

    public static void main(String[] args) {
        System.out.println("It's a camera.");
        // Uncomment the two lines below for XML configuration
        ApplicationContext context = new ClassPathXmlApplicationContext
            ("config.xml");

        // Uncomment the two lines below for using JavaConfig
        //ApplicationContext context =
        //  new AnnotationConfigApplicationContext(AppConfig.class);

        Camera camera = context.getBean("camera", Camera.class);
        camera.makePhoto();
    }
}