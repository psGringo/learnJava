package com.example.main;


import com.example.main.greetings.GreetingsService;
import com.example.main.greetings.GreetingsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public GreetingsService greetingsService() {
        return new GreetingsServiceImpl();
    }
}
