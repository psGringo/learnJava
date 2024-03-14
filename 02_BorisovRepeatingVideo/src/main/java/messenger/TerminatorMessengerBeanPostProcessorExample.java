package messenger;

import messenger.profiling.Profiling;
import messenger.random.RandomCount;
import org.springframework.stereotype.Component;

@Component("BeanPostProcessorExample")
@Profiling
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
