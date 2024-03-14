package messenger;

import jakarta.annotation.PostConstruct;
import messenger.postproxy.PostProxy;
import messenger.profiling.Profiling;
import messenger.random.RandomCount;
import org.springframework.stereotype.Component;

@Component("PhasesExample")
@Profiling
public class TerminatorMessengerPhasesExample implements Messenger {

    @RandomCount(min = 3, max = 7)
    private int repeatCount;

    public TerminatorMessengerPhasesExample() {

        System.out.printf("phase0: constructor, repeatCount %d%n", repeatCount);
    }

    @PostConstruct
    public void init() {
        System.out.printf("phase1: init, repeatCount %d%n", repeatCount);

    }

    @Override
    @PostProxy
    public void sayHello() {
        System.out.printf("called method sayHello: repeatCount %d%n", repeatCount);
        for (int i = 0; i < repeatCount; i++) {
            System.out.println("i'll be back");
        }
    }
}
