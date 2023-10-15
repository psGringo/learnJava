package org.example.animals;

public class SheepFactory extends AnimalFactory {
    @Override
    public Animal create() {
        return new Sheep();
    }
}
