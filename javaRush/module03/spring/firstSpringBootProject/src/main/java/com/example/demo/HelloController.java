package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HelloController {


    private GreetingsService greetingsService;

    @GetMapping("/")
    public String index() {
        return greetingsService.sayHello();
    }
}
