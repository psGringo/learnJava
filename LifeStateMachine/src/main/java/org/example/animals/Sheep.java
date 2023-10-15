package org.example.animals;

public class Sheep extends Animal {
    public Sheep() {
        super(AnimalType.Herbivores);
    }

    @Override
    protected String getName() {
        return "Sheep";
    }
}
