package tutorialspoint;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        //HelloWorld obj = new HelloWorld();
        //obj.setMessage("Hello World!");
        ApplicationContext context = new ClassPathXmlApplicationContext
            ("META-INF/spring/app-context.xml");
        HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
        obj.getMessage();
    }
}