package org.example.animals;

public class WolfFactory extends AnimalFactory {
    @Override
    public Animal create() {
        return new Wolf();
    }
}
