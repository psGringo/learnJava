package com.stanley.borisovSpringDeep.circularDependency;

import java.lang.reflect.Method;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

@Component
public class MainRunnerListener {
    @Autowired
    private ConfigurableListableBeanFactory configurableListableBeanFactory;

    @SneakyThrows
    @EventListener
    public void handleListen(ContextRefreshedEvent contextRefreshedEvent) {
        var context = contextRefreshedEvent.getApplicationContext();
        var beanDefinitionIds = context.getBeanDefinitionNames();
        for (String beanDefinitionId : beanDefinitionIds) {
            var beanDefinition = configurableListableBeanFactory.getBeanDefinition(beanDefinitionId);
            var beanClassName = beanDefinition.getBeanClassName();
            if (beanClassName == null) {
                continue;
            }

            Class<?> beanClass = ClassUtils.resolveClassName(beanClassName, ClassLoader.getSystemClassLoader());
            Method[] methods = beanClass.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Main.class)) {
                    var bean = context.getBean(beanDefinitionId);
                    method.invoke(bean);
                }
            }
        }
    }
}
