package com.example.main.greetings;

import org.openapi.greetings.model.GreetingUI;

public class GreetingsServiceImpl implements GreetingsService {
    @Override
    public GreetingUI getGreeting() {
        return new GreetingUI().message("greeting from backend");
    }
}
