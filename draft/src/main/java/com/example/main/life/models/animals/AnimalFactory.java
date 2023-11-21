package com.example.main.life.models.animals;

import java.util.Random;
import lombok.SneakyThrows;
import org.openapi.life.model.SexUi;
import org.springframework.stereotype.Component;

@Component
public class AnimalFactory {

    private int wolfCount;
    private int sheepCount;

    private void setCommonAnimalProps(Animal animal) {
        animal.setAge(0);
        animal.setHungerSatisfaction(0);
        // set sex
        Random random = new Random();
        SexUi[] sexValues = SexUi.values();
        var randomSexValue = sexValues[random.nextInt(sexValues.length)];
        animal.setSex(randomSexValue);
    }


    @SneakyThrows
    public Wolf createWolf() {
        var wolf = new Wolf();
        setCommonAnimalProps(wolf);
        wolf.setVelocity(2);
        wolf.setName(String.format("wolf_%d", wolfCount++));
        return wolf;
    }

    @SneakyThrows
    public Sheep createSheep() {
        var sheep = new Sheep();
        setCommonAnimalProps(sheep);
        sheep.setVelocity(1);
        sheep.setName(String.format("wolf_%d", sheepCount++));
        return sheep;
    }

    public <T extends Animal> T createAnimal(Class<T> animalClass) {
        if (animalClass.equals(Wolf.class)) {
            return (T) createWolf();
        } else if (animalClass.equals(Sheep.class)) {
            return (T) createSheep();
        } else {
            throw new IllegalArgumentException(String.format("no factory method for class %s", animalClass));
        }
    }
}
