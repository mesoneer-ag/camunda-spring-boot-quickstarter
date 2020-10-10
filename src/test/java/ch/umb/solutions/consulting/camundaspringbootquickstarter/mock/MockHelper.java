package ch.umb.solutions.consulting.camundaspringbootquickstarter.mock;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;

/**
 *  Work around since the Camunda MockExpressionManager does not seem to work for Spring Boot configurations
 *  Examples:
 *  replaceWithMock(context, "loggerDelegate", LoggerDelegateMock.class);
 *  replaceWithMock(context, "sampleDelegate", SampleDelegate.class);
 */
public class MockHelper {
    public static void replaceWithMock(ApplicationContext context, String beanName, Class className) {
        GenericBeanDefinition mockBean = new GenericBeanDefinition();
        mockBean.setBeanClass(className);
        BeanDefinitionRegistry beanDefinitionRegistry = (BeanDefinitionRegistry) context;
        if ( beanDefinitionRegistry.containsBeanDefinition(beanName)) {
            beanDefinitionRegistry.removeBeanDefinition(beanName);
        }
        beanDefinitionRegistry.registerBeanDefinition(beanName, mockBean);
    }
}
