package com.example.main.life.context;

import com.example.main.life.Utils;
import com.example.main.life.models.GameState;
import com.example.main.life.models.animals.Animal;
import com.example.main.life.models.animals.Herbivorial;
import com.example.main.life.models.plants.Grass;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class GrassContext {

    private static void killAndRecoverGrass(List<Grass> grass) {

        var deadGrass = grass.stream().filter(grassItem -> grassItem.getHealth() <= 0).collect(Collectors.toList());
        deadGrass.forEach(deadGrassItem -> {
            grass.remove(deadGrassItem);
        });

        grass.forEach(grassItem -> {
            Utils.recoverHealth(grassItem);
        });
    }

    private static void eatGrassByHerbivorials(List<Grass> grass, List<Animal> animals) {

        var herbivorials = animals.stream().filter(animal -> animal instanceof Herbivorial).collect(Collectors.toList());

        for (int i = 0; i < grass.size(); i++) {
            for (int j = 0; j < herbivorials.size(); j++) {
                var grassItem = grass.get(i);
                var herbivorial = herbivorials.get(j);
                boolean xEquality = (grassItem.getPositionX().intValue() == herbivorial.getPositionX().intValue());
                boolean yEquality = (grassItem.getPositionY().intValue() == herbivorial.getPositionY().intValue());
                if (xEquality && yEquality) {
                    Utils.exchangeHealth(herbivorial, grassItem);
                }
            }
        }
    }

    public static void setNextState(GameState stateClone, GameState state) {
        var aliveGrass = Utils.getAliveGodParticles(stateClone.getGrass());
        killAndRecoverGrass(aliveGrass);
        eatGrassByHerbivorials(aliveGrass, stateClone.getAnimals());
        state.setGrass(aliveGrass);
        state.setAnimals(stateClone.getAnimals());
    }
}
