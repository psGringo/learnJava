package com.example.main.greetings;

import org.openapi.life.model.GreetingUi;
import org.springframework.beans.factory.annotation.Value;

//@Component, alternatievely to AppConfig
//@Service
public class GreetingsServiceImpl implements GreetingsService {

    @Value("${myProperty:defaultValue}")
    private String property;

    @Override
    public GreetingUi getGreeting() {
        String propValue = property;
        return new GreetingUi().message("greeting from backend");
    }
}
