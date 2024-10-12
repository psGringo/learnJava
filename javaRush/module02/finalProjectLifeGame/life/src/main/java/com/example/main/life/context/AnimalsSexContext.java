package com.example.main.life.context;

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
        for (int i = 0; i < animals.size(); i++) {
            for (int j = 0; j < animals.size(); j++) {
                if (i > j) {
                    var animal = animals.get(i);
                    var otherAnimal = animals.get(j);
                    boolean xEquality = animal.getPositionX().equals(otherAnimal.getPositionX());
                    boolean yEquality = animal.getPositionY().equals(otherAnimal.getPositionY());
                    boolean typeEquality = animal.getClass().equals(otherAnimal.getClass());
                    boolean isDifferentSex = !animal.getSex().equals(otherAnimal.getSex());
                    if (xEquality && yEquality && isDifferentSex && typeEquality) {
                        animalPairs.add(new AnimalPair(animal, otherAnimal));
                    }
                }
            }
        }

        animalPairs.forEach(animalPair -> {
            // having sex here
            Animal baby = animalFactory.createAnimal(animalPair.getOne().getClass());
            baby.setPositionX(animalPair.getOne().getPositionX());
            baby.setPositionY(animalPair.getOne().getPositionY());
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
