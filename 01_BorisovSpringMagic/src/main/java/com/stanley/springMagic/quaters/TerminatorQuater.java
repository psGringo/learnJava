package com.stanley.springMagic.quaters;

import jakarta.annotation.PostConstruct;
import lombok.Setter;

@Setter
@Profiling
public class TerminatorQuater implements Quoter {
    @InjectRandomInt(min = 2, max = 7)
    private int repeat;

    private String message;

    @PostConstruct
    public void init() {
        System.out.println("Phase 2, init method");
        System.out.println(repeat);
    }

    public TerminatorQuater() {
        System.out.println("Phase 1, constructor");
        System.out.println(repeat);
    }


    @Override
    @PostProxy
    public void sayQuote() {
        System.out.println("Phase 3, PostProxy");
        for (int i = 0; i < repeat; i++) {
            System.out.println("message = " + message);
        }
    }

}
