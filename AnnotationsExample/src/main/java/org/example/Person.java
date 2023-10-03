package org.example;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Person {

    @Getter
    @Setter
    private String name = "default";
}
