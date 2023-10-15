package org.example.animals;

public class Wolf extends Animal {

    public Wolf() {
        super(AnimalType.Predator);
    }

    @Override
    protected String getName() {
        return "Wolf";
    }
}
