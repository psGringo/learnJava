package messenger.postproxyContextRefreshed;

import java.lang.reflect.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class PostProxyListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ConfigurableListableBeanFactory configurableListableBeanFactory;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition(beanDefinitionName);
            String originalClassName = beanDefinition.getBeanClassName();
            if (originalClassName != null) {
                try {
                    Class<?> originalClass = Class.forName(originalClassName);
                    for (Method method : originalClass.getMethods()) {
                        if (method.isAnnotationPresent(PostProxy.class)) {
                            Object bean = applicationContext.getBean(beanDefinitionName);
                            Method postProxyMethod = bean.getClass().getMethod(method.getName(), method.getParameterTypes());
                            System.out.println("this is fired on each context refresh");
                            System.out.printf("invoking method %s on context refreshed from post proxy%n", postProxyMethod.getName());
                            postProxyMethod.invoke(bean);
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
