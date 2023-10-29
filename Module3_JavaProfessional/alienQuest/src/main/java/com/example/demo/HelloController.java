package com.example.demo;

import lombok.AllArgsConstructor;
import org.openapi.example.model.Greeting;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class HelloController implements org.openapi.example.api.GreetingsApi {


    private GreetingsService greetingsService;

    @GetMapping("/")
    public String index() {
        return greetingsService.sayHello();
    }

    @Override
    public ResponseEntity<Greeting> sayHello() {
        var greeting = new Greeting();
        greeting.message("hello");
        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }
}
