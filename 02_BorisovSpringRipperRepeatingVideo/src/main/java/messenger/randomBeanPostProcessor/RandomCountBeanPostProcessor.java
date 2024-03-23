package messenger.randomBeanPostProcessor;

import java.util.Random;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component
public class RandomCountBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        var fields = bean.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].isAnnotationPresent(RandomCount.class)) {
                RandomCount randomCountAnnotation = fields[i].getAnnotation(RandomCount.class);
                if (randomCountAnnotation != null) {
                    Random random = new Random();
                    int randomValue = randomCountAnnotation.min() + random.nextInt(randomCountAnnotation.max() - randomCountAnnotation.min());
                    fields[i].setAccessible(true);
                    ReflectionUtils.setField(fields[i], bean, randomValue);
                }
            }
        }

        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
