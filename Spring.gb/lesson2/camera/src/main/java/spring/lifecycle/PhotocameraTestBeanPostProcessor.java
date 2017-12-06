package spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import spring.Camera;

@Component
public class PhotocameraTestBeanPostProcessor implements BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean; // just return bean
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // find bean cameras class
        if (bean instanceof Camera) {
            System.out.println("Making test photo...");
            ((Camera) bean).makePhoto();
            System.out.println("Everything works OK.");
        }
        return bean;
    }
}