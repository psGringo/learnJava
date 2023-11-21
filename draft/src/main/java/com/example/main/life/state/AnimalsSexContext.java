package com.example.main.life.state;

import com.example.main.life.models.animals.Animal;
import com.example.main.life.models.animals.AnimalFactory;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnimalsSexContext {

    @Autowired
    AnimalFactory animalFactory;

    public void sex(List<Animal> animals) {

        List<AnimalPair> animalPairs = new ArrayList<>();

        animals.forEach(animal -> {
            animals.forEach(otherAnimal -> {
                boolean xEquality = animal.getPositionX().equals(otherAnimal.getPositionX());
                boolean yEquality = animal.getPositionY().equals(otherAnimal.getPositionY());
                boolean typeEquality = animal.getClass().equals(otherAnimal.getClass());
                boolean isDifferentSex = !animal.getSex().equals(otherAnimal.getSex());
                if (xEquality && yEquality && isDifferentSex && typeEquality) {
                    animalPairs.add(new AnimalPair(animal, otherAnimal));
                }
            });
        });

        animalPairs.forEach(animalPair -> {
            // having sex here
            Animal baby = animalFactory.createAnimal(animalPair.getOne().getClass());
            animals.add(baby);
        });

    }
}

@Getter
@Setter
@AllArgsConstructor
class AnimalPair {
    private Animal one;
    private Animal another;
}
