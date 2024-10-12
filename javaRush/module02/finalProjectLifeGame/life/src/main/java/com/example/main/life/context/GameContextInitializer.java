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
        sheep.setPositionY(8);

        var sheep2 = animalFactory.createSheep();
        sheep.setPositionX(6);
        sheep.setPositionY(6);

//        var sheep3 = animalFactory.createSheep();
//        sheep.setPositionX(7);
//        sheep.setPositionY(7);

        var wolf = animalFactory.createOneWolfInTheTopLeftCorner();

        var wolf2 = animalFactory.createWolf();
        wolf2.setPositionX(2);
        wolf2.setPositionY(2);

        var wolf3 = animalFactory.createWolf();
        wolf3.setPositionX(6);
        wolf3.setPositionY(3);

//        var wolf4 = animalFactory.createWolf();
//        wolf4.setPositionX(7);
//        wolf4.setPositionY(4);

        animals.add(sheep);
//        animals.add(sheep2);
//        animals.add(sheep3);
        animals.add(wolf);
        animals.add(wolf2);
        animals.add(wolf3);
//        animals.add(wolf4);
        gameState.setAnimals(animals);
        // grass
        var grass = plantFactory.createRandomlyDistributedMapOfGrass(gameState.getMap());
        gameState.setGrass(grass);

        return gameState;
    }

}
