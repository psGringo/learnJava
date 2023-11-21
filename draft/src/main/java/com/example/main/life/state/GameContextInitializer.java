package com.example.main.life.state;

import com.example.main.life.models.GameState;
import com.example.main.life.models.animals.Animal;
import com.example.main.life.models.animals.AnimalFactory;
import com.example.main.life.models.plants.Grass;
import java.util.ArrayList;
import java.util.List;
import org.openapi.life.model.MovementDirectionUi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameContextInitializer {

    @Autowired
    AnimalFactory animalFactory;

    public GameState testOneAnimal() {
        GameState gameState = new GameState();

        // map
        gameState.getMap().setMaxX(10);
        gameState.getMap().setMaxY(10);

        // animals
        var sheep = animalFactory.createSheep();
        sheep.setPositionX(0);
        sheep.setPositionX(0);
        sheep.setMovementDirection(MovementDirectionUi.RIGHT);
        List<Animal> animals = new ArrayList<>();
        animals.add(sheep);
        gameState.setAnimals(animals);

        // grass
        List<Grass> grass = new ArrayList<>();
        for (int i = 0; i < gameState.getMap().getMaxX(); i++) {
            for (int j = 0; j < gameState.getMap().getMaxY(); j++) {
                Grass grassItem = new Grass();
                grass.add(grassItem);
            }
        }


        return gameState;
    }
}
