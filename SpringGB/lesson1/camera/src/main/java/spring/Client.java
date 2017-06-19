package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.context.annotation.*;

class Client {

    public static void main(String[] args) {
        System.out.println("It's a camera.");
        // Раскомментировать две строки ниже, для XML-конфигурирования
        ApplicationContext context = new ClassPathXmlApplicationContext
            ("config.xml");

        // Раскомментируйте две строки ниже для использования JavaConfig
        //ApplicationContext context =
        //  new AnnotationConfigApplicationContext(AppConfig.class);

        //Camera camera = context.getBean("camera", Camera.class);
        //camera.doPhotograph();
    }
}