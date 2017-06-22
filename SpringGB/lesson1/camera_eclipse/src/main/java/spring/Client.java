package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {

    public static void main(String[] args) {
        System.out.println("It's a camera.");
        // ����������������� ��� ������ ����, ��� XML-����������������
        ApplicationContext context = new ClassPathXmlApplicationContext
            ("config.xml");

        // ���������������� ��� ������ ���� ��� ������������� JavaConfig
        //ApplicationContext context =
        //  new AnnotationConfigApplicationContext(AppConfig.class);

        Camera camera = context.getBean("camera", Camera.class);
        camera.doPhotograph();
    }
}