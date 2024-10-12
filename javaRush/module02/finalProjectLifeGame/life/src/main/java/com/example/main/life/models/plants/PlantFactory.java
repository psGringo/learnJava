package com.example.main.life.models.plants;

import com.example.main.life.models.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PlantFactory {

    @Value("#{T(java.lang.Integer).parseInt('${maxHealth}')}")
    private int maxHealth;

    private Grass createGrassItem(int positionX, int positionY, int orderNumber) {
        Grass grassItem = new Grass();
        grassItem.setPositionX(positionX);
        grassItem.setPositionY(positionY);
        grassItem.setHealth(maxHealth);
        grassItem.setHungerSatisfaction(0);
        grassItem.setAge(0);
        grassItem.setName(String.format("grass_item_%s", orderNumber));
        return grassItem;
    }

    public List<Grass> createFullMapOfGrass(Map map) {

        int countGrassItems = 0;

        List<Grass> grassItems = new ArrayList<>();
        for (int i = 0; i < map.getMaxX(); i++) {
            for (int j = 0; j < map.getMaxY(); j++) {
                var newGrassItem = createGrassItem(i, j, countGrassItems++);
                grassItems.add(newGrassItem);
            }
        }

        return grassItems;
    }

    public List<Grass> createRandomlyDistributedMapOfGrass(Map map) {

        int countGrassItems = 0;
        var random = new Random();

        List<Grass> grassItems = new ArrayList<>();
        for (int i = 0; i < map.getMaxX(); i++) {
            for (int j = 0; j < map.getMaxY(); j++) {
                var needGrowGrass = random.nextBoolean();
                if (needGrowGrass) {
                    var newGrassItem = createGrassItem(i, j, countGrassItems++);
                    grassItems.add(newGrassItem);
                }
            }
        }

        return grassItems;
    }
}
