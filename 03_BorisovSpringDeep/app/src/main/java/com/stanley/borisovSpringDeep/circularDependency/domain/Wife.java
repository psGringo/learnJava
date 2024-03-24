package com.stanley.borisovSpringDeep.circularDependency.domain;

import com.stanley.borisovSpringDeep.circularDependency.Main;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Wife {

    @Getter
    private int love;

    @Autowired
    private Husband husband;


    @Main
    public void doStuff() {
        init();
        System.out.println(String.format("got money from husband %d", husband.getMoney()));
    }

    @PostConstruct
    public void init() {
        love = 1000;
    }
}
