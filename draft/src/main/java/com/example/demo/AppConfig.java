package com.example.demo;


import com.example.demo.greetings.GreetingsService;
import com.example.demo.greetings.GreetingsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public GreetingsService greetingsService() {
        return new GreetingsServiceImpl();
    }
}
