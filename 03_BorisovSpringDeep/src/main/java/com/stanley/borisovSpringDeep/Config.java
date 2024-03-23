package com.stanley.borisovSpringDeep;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("myapplication.properties")
public class Config {

    @Value("${mymessage}")
    private String message;

    @PostConstruct
    public void printMsg() {
        System.out.println(message);
    }

    // works by default but you can still define it here
    @Bean
    public static PropertySourcesPlaceholderConfigurer configurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
