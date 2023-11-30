package com.example.main.greetings;

import org.openapi.greetings.model.GreetingUI;


import org.springframework.beans.factory.annotation.Value;

//@Component, alternatievely to AppConfig
//@Service
public class GreetingsServiceImpl implements GreetingsService {

    @Value("${myProperty:defaultValue}")
    private String property;

    @Override
    public org.openapi.greetings.model.GreetingUI getGreeting() {
        String propValue = property;
        return new GreetingUI().message("greeting from backend");
    }
}
