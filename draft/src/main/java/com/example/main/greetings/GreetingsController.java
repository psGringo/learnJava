package com.example.main.greetings;

import lombok.AllArgsConstructor;
import org.openapi.life.api.GreetingsApi;
import org.openapi.life.model.GreetingUi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class GreetingsController implements GreetingsApi {

    private GreetingsService greetingsService;

    @Override
    public ResponseEntity<GreetingUi> sayHello() {
        return new ResponseEntity<>(greetingsService.getGreeting(), HttpStatus.OK);
    }
}
