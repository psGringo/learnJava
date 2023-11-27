package com.example.main.life.context;

import com.example.main.life.models.GameState;
import com.example.main.life.models.animals.Animal;
import com.example.main.life.models.animals.AnimalFactory;
import com.example.main.life.models.plants.PlantFactory;
import java.util.ArrayList;
import java.util.List;
import org.openapi.life.model.SexUi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameContextInitializer {

    @Autowired
    AnimalFactory animalFactory;

    @Autowired
    PlantFactory plantFactory;

    public GameState testOneSheepMoves() {
        GameState gameState = new GameState();
        // map
        gameState.getMap().setMaxX(5);
        gameState.getMap().setMaxY(5);
        // animals
        List<Animal> animals = new ArrayList<>();
        var sheep = animalFactory.createOneSheepInTheTopLeftCorner();
        animals.add(sheep);
        gameState.setAnimals(animals);

        return gameState;
    }

    public GameState testOneSheepMovesAndEatGrass() {
        GameState gameState = new GameState();
        // map
        gameState.getMap().setMaxX(5);
        gameState.getMap().setMaxY(5);
        // animals
        List<Animal> animals = new ArrayList<>();
        var sheep = animalFactory.createOneSheepInTheTopLeftCorner();
        animals.add(sheep);
        gameState.setAnimals(animals);
        // grass
        var grass = plantFactory.createRandomlyDistributedMapOfGrass(gameState.getMap());
        gameState.setGrass(grass);

        return gameState;
    }

    public GameState testWolfEatSheep() {
        GameState gameState = new GameState();
        // map
        gameState.getMap().setMaxX(5);
        gameState.getMap().setMaxY(5);
        // animals
        List<Animal> animals = new ArrayList<>();
        var sheep = animalFactory.createOneSheepInTheTopLeftCorner();
        var wolf = animalFactory.createOneWolfInTheTopLeftCorner();
        animals.add(sheep);
        animals.add(wolf);
        gameState.setAnimals(animals);
        // grass
        var grass = plantFactory.createRandomlyDistributedMapOfGrass(gameState.getMap());
        gameState.setGrass(grass);

        return gameState;
    }

    public GameState testWolfHavingSex() {
        GameState gameState = new GameState();
        // map
        gameState.getMap().setMaxX(5);
        gameState.getMap().setMaxY(5);
        // animals
        List<Animal> animals = new ArrayList<>();

        var wolfMale = animalFactory.createOneWolfInTheTopLeftCorner();
        wolfMale.setSex(SexUi.MALE);

        var wolfFemale = animalFactory.createOneWolfInTheTopLeftCorner();
        wolfFemale.setSex(SexUi.FEMALE);

        animals.add(wolfMale);
        animals.add(wolfFemale);
        gameState.setAnimals(animals);
        // grass
        var grass = plantFactory.createRandomlyDistributedMapOfGrass(gameState.getMap());
        gameState.setGrass(grass);

        return gameState;
    }

    public GameState testWolfAndSheep() {
        GameState gameState = new GameState();
        // map
        gameState.getMap().setMaxX(10);
        gameState.getMap().setMaxY(10);
        // animals
        List<Animal> animals = new ArrayList<>();
        var sheep = animalFactory.createSheep();
        sheep.setPositionX(5);
        sheep.setPositionY(5);

        var wolf = animalFactory.createOneWolfInTheTopLeftCorner();
        animals.add(sheep);
        animals.add(wolf);
        gameState.setAnimals(animals);
        // grass
        var grass = plantFactory.createRandomlyDistributedMapOfGrass(gameState.getMap());
        gameState.setGrass(grass);

        return gameState;
    }

}
