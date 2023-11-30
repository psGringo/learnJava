package com.example.main.life.controllers;

import com.example.main.life.Statistics;
import com.example.main.life.mappers.GameMapMapper;
import com.example.main.life.context.GameContext;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.openapi.life.model.GameStateUi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LifeController implements org.openapi.life.api.LifeApi {

    GameContext gameContext;

    @Override
    public ResponseEntity<GameStateUi> getGameState() {

        gameContext.getNextState();

        var gameMapMapper = GameMapMapper.INSTANCE;
        var map = gameMapMapper.toMapDto(gameContext.getState().getMap());

        var grass = gameContext.getState().getGrass().stream().map(
                grassItem -> gameMapMapper.toGrassDto(grassItem)
        ).collect(Collectors.toList());

        var animals = gameContext.getState().getAnimals().stream().map(
                animal -> gameMapMapper.toAnimalsDto(animal)
        ).collect(Collectors.toList());

        GameStateUi gameStateUi = new GameStateUi();
        gameStateUi.gameMap(map);
        gameStateUi.setGrass(grass);
        gameStateUi.setAnimals(animals);
        gameStateUi.setStatistics(gameMapMapper.toStatistcsDto(Statistics.getInstance()));

        return new ResponseEntity<>(gameStateUi, HttpStatus.OK);
    }
}
