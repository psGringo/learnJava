package org.example.animals;

import lombok.Getter;

public class Wolf extends Animal {

    public Wolf() {
        super(AnimalType.Predator);
    }

    @Override
    public String getName() {
        return "Wolf";
    }
}
