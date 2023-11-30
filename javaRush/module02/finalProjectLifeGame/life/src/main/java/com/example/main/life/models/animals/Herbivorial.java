package com.example.main.life.models.animals;

import com.example.main.life.models.plants.Plant;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class Herbivorial extends Animal{
    @Getter
    @Setter
    private List<Plant> eatenPlants;
}
