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

//    @GetMapping("/greetings")
//    public String index() {
//        return greetingsService.sayHello();
//    }

    @Override
    public ResponseEntity<GreetingUI> sayHello() {
        var greeting = new GreetingUI();
        greeting.message("hello");
        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }
}
