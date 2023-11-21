package com.example.main.life.state;

import com.example.main.life.Utils;
import com.example.main.life.models.GameState;
import com.example.main.life.models.animals.Animal;
import com.example.main.life.models.animals.Herbivorial;
import com.example.main.life.models.plants.Grass;
import java.util.List;

public class GrassContext {

    private static void recoverGrass(List<Grass> grass) {

        grass.forEach(grassItem -> {
            Utils.recoverHealth(grassItem);
        });
    }

    private static void eatGrassByHerbivorials(List<Grass> grass, List<Animal> animals) {

        var herbivorials = animals.stream().filter(animal -> animal instanceof Herbivorial);
        herbivorials.filter(herbivorial -> grass.stream().anyMatch((grassItem) -> {
            boolean xEquality = (grassItem.getPositionX().intValue() == herbivorial.getPositionX().intValue());
            boolean yEquality = (grassItem.getPositionY().intValue() == herbivorial.getPositionY().intValue());
            if (xEquality && yEquality) {
                Utils.exchangeHealth(herbivorial, grassItem);
            }
            return (xEquality && yEquality);
        }));
    }

    public static void setNextState(GameState stateClone, GameState state) {
        var aliveGrass = Utils.getAliveGodParticles(stateClone.getGrass());
        recoverGrass(aliveGrass);
        eatGrassByHerbivorials(aliveGrass, stateClone.getAnimals());
        state.setGrass(aliveGrass);
        state.setAnimals(stateClone.getAnimals());
    }
}
