package com.example.main.greetings;

import org.openapi.greetings.model.GreetingUI;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component, alternatievely to AppConfig
//@Service
public class GreetingsServiceImpl implements GreetingsService {
    @Override
    public GreetingUI getGreeting() {
        return new GreetingUI().message("greeting from backend");
    }
}
