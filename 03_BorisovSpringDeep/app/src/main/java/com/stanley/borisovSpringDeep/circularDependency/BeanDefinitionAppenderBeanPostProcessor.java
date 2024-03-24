package com.stanley.borisovSpringDeep.circularDependency;

import java.util.Arrays;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class BeanDefinitionAppenderBeanPostProcessor implements BeanPostProcessor {
    @Autowired
    ConfigurableListableBeanFactory configurableListableBeanFactory;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Arrays.stream(configurableListableBeanFactory.getBeanDefinitionNames()).parallel().forEach(name -> {
            if (neededMainBehaviour(bean)) {
                BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition(beanName);
                if (beanDefinition.getBeanClassName() == null) {
                    beanDefinition.setBeanClassName(bean.getClass().getCanonicalName());
                }
            }
        });
        return bean;
    }

    private boolean neededMainBehaviour(Object bean) {
        return Arrays.stream(bean.getClass().getMethods()).anyMatch(method -> method.isAnnotationPresent(Main.class));
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
