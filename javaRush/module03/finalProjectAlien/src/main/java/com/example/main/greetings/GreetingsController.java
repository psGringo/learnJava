package com.example.main.greetings;

import lombok.AllArgsConstructor;
import org.openapi.greetings.model.GreetingUI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class GreetingsController implements org.openapi.greetings.api.GreetingsApi {

    private GreetingsService greetingsService;

    @Override
    public ResponseEntity<GreetingUI> sayHello() {
        return new ResponseEntity<>(greetingsService.getGreeting(), HttpStatus.OK);
    }
}
