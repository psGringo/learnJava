package com.example.main.life.models.animals;

import java.util.Random;
import lombok.SneakyThrows;
import org.openapi.life.model.SexUi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AnimalFactory {

    private int wolfCount;
    private int sheepCount;
    @Value("#{T(java.lang.Integer).parseInt('${maxHealth}')}")
    private int maxHealth;

    private void setCommonAnimalProps(Animal animal) {
        animal.setHealth(maxHealth);
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
        wolf.setAge(0);
        setCommonAnimalProps(wolf);
        wolf.setVelocity(1);
        wolf.setName(String.format("wolf_%d", wolfCount++));
        return wolf;
    }

    @SneakyThrows
    public Sheep createSheep() {
        var sheep = new Sheep();
        sheep.setAge(0);
        setCommonAnimalProps(sheep);
        sheep.setVelocity(1);
        sheep.setName(String.format("sheep_%d", sheepCount++));
        return sheep;
    }

    @SneakyThrows
    public Sheep createOneSheepInTheTopLeftCorner() {
        var sheep = createSheep();
        sheep.setPositionX(0);
        sheep.setPositionY(0);
        sheep.setMovementDirection(org.openapi.life.model.MovementDirectionUi.RIGHT);
        return sheep;
    }

    @SneakyThrows
    public Wolf createOneWolfInTheTopLeftCorner() {
        var wolf = createWolf();
        wolf.setPositionX(0);
        wolf.setPositionY(0);
        wolf.setMovementDirection(org.openapi.life.model.MovementDirectionUi.RIGHT);
        return wolf;
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
