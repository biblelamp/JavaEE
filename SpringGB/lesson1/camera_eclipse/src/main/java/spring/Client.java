package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {

    public static void main(String[] args) {

        System.out.println("It's a camera.");

        // Remove the comment of two lines below for XML configuring
        ApplicationContext context = new ClassPathXmlApplicationContext
            ("config.xml");

        // Remove the comment of two lines below for JavaConfig
        //ApplicationContext context =
        //  new AnnotationConfigApplicationContext(AppConfig.class);

        Camera camera = context.getBean("camera", Camera.class);
        camera.doPhotograph();
    }
}