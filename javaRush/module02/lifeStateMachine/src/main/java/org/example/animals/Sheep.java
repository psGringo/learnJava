package org.example.animals;

public class Sheep extends Animal {
    public Sheep() {
        super(AnimalType.Herbivores);
    }

    @Override
    public String getName() {
        return "Sheep";
    }
}
