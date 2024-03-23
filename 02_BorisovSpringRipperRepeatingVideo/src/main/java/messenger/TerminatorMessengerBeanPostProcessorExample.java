package messenger;

import messenger.beanFactoryPostProcessorEample.DeprecatedClass;
import messenger.profiling.Profiling;
import messenger.randomBeanPostProcessor.RandomCount;
import org.springframework.stereotype.Component;

@Component("BeanPostProcessorExample")
@Profiling
@DeprecatedClass(newImpl = TerminatorMessengerBeanPostProcessorExample.class)
public class TerminatorMessengerBeanPostProcessorExample implements Messenger {

    @RandomCount(min = 3, max = 7)
    private int repeatCount;


    @Override
    public void sayHello() {
        for (int i = 0; i < repeatCount; i++) {
            System.out.println("i'll be back");
        }
    }
}
