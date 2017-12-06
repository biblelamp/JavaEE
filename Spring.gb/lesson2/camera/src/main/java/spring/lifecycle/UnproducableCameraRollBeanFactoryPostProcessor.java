package spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import spring.annotation.UnproducableCameraRoll;

@Component
public class UnproducableCameraRollBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
            throws BeansException {

        // get names of all BeanDefinition
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();

        for (String name: beanDefinitionNames) {

            // get BeanDefinition by name
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);

            /* получаем имя класса создавамого бина, чтобы проверить , 
             * содержит ли он аннотацию UnsupportedCameraRoll 
             */ 
            String className = beanDefinition.getBeanClassName();

            try {
                // get class by name
                Class<?> beanClass = Class.forName(className);

                /* пытаемся получить объект аннотации и ее значение,
                 * если  класс не содержит данную аннотацию, то  метод вернет null
                 */
                UnproducableCameraRoll annotation = beanClass.getAnnotation(UnproducableCameraRoll.class);

                // проверяем, содержал ли класс эту аннотацию
                if (annotation != null) {
                    // получаем значение указанное в параметрах аннотации(класс пленки, которую необходимо использовать)
                    Class usingCameraRollName =annotation.usingCameraRollClass();
                    // меняем класс будущего бина!
                    beanDefinition.setBeanClassName(usingCameraRollName.getName());	
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}