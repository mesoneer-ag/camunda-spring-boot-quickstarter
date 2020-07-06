package ch.umb.solutions.consulting.camundaspringbootquickstarter.mock;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;

public class MockHelper {
    public static void registerMock(ApplicationContext context, String beanName, Class className) {
        GenericBeanDefinition mockBean = new GenericBeanDefinition();
        mockBean.setBeanClass(className);
        ((BeanDefinitionRegistry) context).removeBeanDefinition(beanName);
        ((BeanDefinitionRegistry) context).registerBeanDefinition(beanName, mockBean);


    }
}
