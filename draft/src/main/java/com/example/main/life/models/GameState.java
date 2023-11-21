package com.example.main.life.models;

import com.example.main.life.models.animals.Animal;
import com.example.main.life.models.plants.Grass;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

public class GameState {
    @Getter
    @Setter
    private Map map;
    @Getter
    @Setter
    private List<Grass> grass;
    @Getter
    @Setter
    private List<Animal> animals;

    @SneakyThrows
    public GameState getClone() {
        ObjectMapper objectMapper = new ObjectMapper();
        GameState deepCopy = objectMapper.readValue(objectMapper.writeValueAsString(this), GameState.class);
        return deepCopy;
    }
}
