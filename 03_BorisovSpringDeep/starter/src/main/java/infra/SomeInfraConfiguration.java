package infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SomeInfraConfiguration {
    @Bean
    public SomeInfraBean someInfraBean() {
        return new SomeInfraBean();
    }
}
