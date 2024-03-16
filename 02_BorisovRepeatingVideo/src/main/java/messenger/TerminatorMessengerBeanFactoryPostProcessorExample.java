package messenger;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("BeanFactoryPostProcessorExample")
public class TerminatorMessengerBeanFactoryPostProcessorExample implements Messenger {
    @Override
    public void sayHello() {
        System.out.println("i'm liquid");
    }
}
