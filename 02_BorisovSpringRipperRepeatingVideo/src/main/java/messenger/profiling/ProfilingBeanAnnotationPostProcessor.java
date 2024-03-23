package messenger.profiling;

import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import lombok.SneakyThrows;
import messenger.profiling.mbean.ProfilingController;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.stereotype.Component;

@Component
public class ProfilingBeanAnnotationPostProcessor implements BeanPostProcessor {

    private Map<String, Class> map = new HashMap<>();
    private ProfilingController profilingController;

    @SneakyThrows
    public ProfilingBeanAnnotationPostProcessor() {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        profilingController = new ProfilingController();
        mBeanServer.registerMBean(profilingController, new ObjectName("profiling", "name", "controller"));
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(Profiling.class)) {
            map.put(beanName, beanClass);
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = map.get(beanName);
        if (beanClass != null) {

            return Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(), (proxy, method, args) -> {
                if (profilingController.isEnabled()) {
                    System.out.println("Profiling...");
                    long before = System.nanoTime();
                    Object returnValue = method.invoke(bean, args);
                    long after = System.nanoTime();
                    System.out.println(after - before);
                    System.out.println("Finished Profiling...");
                    return returnValue;
                } else {
                    return method.invoke(bean, args);
                }
            });
        }

        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
