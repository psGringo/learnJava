package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HelloController implements org.openapi.greeting.GreetingsApi {


    private GreetingsService greetingsService;

    @GetMapping("/")
    public String index() {
        return greetingsService.sayHello();
    }

    @Override
    public ResponseEntity<org.openapi.greeting.model.Greeting> sayHello() {
        var greeting = new org.openapi.greeting.model.Greeting();
        greeting.message("hello");
        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }
}
