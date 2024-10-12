package com.example.main.life;

import com.example.main.life.models.GameState;
import com.example.main.life.models.animals.Herbivorial;
import com.example.main.life.models.animals.Predator;
import com.example.main.observer.Listener;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.SneakyThrows;


@Getter
public class Statistics implements Listener {

    private int totalGodParticles;
    private int totalAliveGrass;
    private int totalAliveAnimals;
    private int totalAlivePredators;
    private int totalAliveHerbivorials;

    private static Statistics instance;

    private Statistics() {
    }

    public static Statistics getInstance() {
        if (instance == null)
            instance = new Statistics();

        return instance;
    }

    @SneakyThrows
    @Override
    public void listen(Object data) {
        var gameState = (GameState) data;

        var threadExecutor = Executors.newSingleThreadExecutor();
        Future future = threadExecutor.submit(() -> {

            totalAliveGrass = gameState.getGrass().stream().filter(
                    grass -> grass.getHealth() > 0 && grass.getAge() < 99 // TODO magic numbers
            ).collect(Collectors.toList()).size();

            totalAliveAnimals = gameState.getAnimals().stream().filter(
                    animal -> animal.getHealth() > 0 && animal.getAge() < 99
            ).collect(Collectors.toList()).size();

            totalAlivePredators = gameState.getAnimals().stream().filter(
                    animal -> animal instanceof Predator &&
                            animal.getHealth() > 0 && animal.getAge() < 99
            ).collect(Collectors.toList()).size();

            totalAliveHerbivorials = gameState.getAnimals().stream().filter(
                    animal -> animal instanceof Herbivorial &&
                            animal.getHealth() > 0 && animal.getAge() < 99
            ).collect(Collectors.toList()).size();

            totalGodParticles = totalAliveGrass + totalAliveAnimals +
                    totalAlivePredators + totalAliveHerbivorials;

        });

        future.get();
    }
}
