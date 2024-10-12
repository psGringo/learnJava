package com.example.main.life.context;

import com.example.main.life.Utils;
import com.example.main.life.models.GameState;
import com.example.main.life.models.animals.Animal;
import com.example.main.life.models.animals.Herbivorial;
import com.example.main.life.models.animals.Predator;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Getter
@Setter
class AnimalCollision {
    Predator predator;
    Herbivorial herbivorial;
}

@Component
public class AnimalsContext {

    @Autowired
    AnimalsSexContext animalsSexContext;
    @Autowired
    AnimalContextMove animalContextMove;

    private boolean canHunt(Animal oneAnimal, Animal anotherAnimal) {
        return (oneAnimal instanceof Herbivorial && anotherAnimal instanceof Predator) ||
                (anotherAnimal instanceof Herbivorial && oneAnimal instanceof Predator);
    }

    private Predator getPredator(Animal oneAnimal, Animal anotherAnimal) {
        return oneAnimal instanceof Predator ? (Predator) oneAnimal : (Predator) anotherAnimal;
    }

    private Herbivorial getHerbivorial(Animal oneAnimal, Animal anotherAnimal) {
        return oneAnimal instanceof Herbivorial ? (Herbivorial) oneAnimal : (Herbivorial) anotherAnimal;
    }

    private List<AnimalCollision> getAnimalsCollisions(List<Animal> animals) {
        List<AnimalCollision> collisions = new ArrayList<>();

        for (int i = 0; i < animals.size(); i++) {
            for (int j = 0; j < animals.size(); j++) {
                if (i > j) {
                    var oneAnimal = animals.get(i);
                    var anotherAnimal = animals.get(j);
                    boolean xEquality = oneAnimal.getPositionX().equals(anotherAnimal.getPositionX());
                    boolean yEquality = oneAnimal.getPositionY().equals(anotherAnimal.getPositionY());
                    boolean canHunt = canHunt(oneAnimal, anotherAnimal);
                    if (xEquality && yEquality && canHunt) {
                        var predator = getPredator(oneAnimal, anotherAnimal);
                        var herbivorial = getHerbivorial(oneAnimal, anotherAnimal);
                        collisions.add(new AnimalCollision(predator, herbivorial));
                    }
                }
            }
        }

        return collisions;
    }

    public void eat(List<Animal> animals) {

        var collisions = getAnimalsCollisions(animals);

        collisions.forEach(animalCollision -> {
            Utils.exchangeHealth(animalCollision.getPredator(), animalCollision.getHerbivorial());
        });
    }

    public void setNextState(GameState stateClone, GameState state) {
        var aliveAnimals = Utils.getAliveGodParticles(stateClone.getAnimals());
        eat(aliveAnimals);
        animalsSexContext.sex(aliveAnimals);
        animalContextMove.move(aliveAnimals, stateClone.getMap());
        state.setAnimals(aliveAnimals);
    }
}
