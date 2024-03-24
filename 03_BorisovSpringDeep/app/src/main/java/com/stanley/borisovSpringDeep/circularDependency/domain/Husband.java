package com.stanley.borisovSpringDeep.circularDependency.domain;

import com.stanley.borisovSpringDeep.circularDependency.Main;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Husband {

    @Getter
    private int money;

    @Autowired
    private Wife wife;


    @Main
    public void doStuff() {
        init();
        System.out.println(String.format("got love from wife %d", wife.getLove()));
    }

    @PostConstruct
    public void init() {
        money = 1000;
    }
}
