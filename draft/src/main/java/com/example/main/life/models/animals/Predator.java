package com.example.main.life.models.animals;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class Predator extends Animal {
    @Getter
    @Setter
    private List<Animal> eatenAnimals;
}
