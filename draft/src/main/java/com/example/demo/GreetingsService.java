package com.example.demo;

import org.springframework.context.annotation.Bean;

import java.beans.JavaBean;

@JavaBean
public interface GreetingsService {
    String sayHello();
}
