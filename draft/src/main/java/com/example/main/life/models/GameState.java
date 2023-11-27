package com.example.main.life.models;

import com.example.main.life.models.animals.Animal;
import com.example.main.life.models.plants.Grass;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;


@Getter
@Setter
public class GameState implements Cloneable {

    private Map map;
    private List<Grass> grass;
    private List<Animal> animals;

    public GameState() {
        map = new Map();
        grass = new ArrayList<>();
        animals = new ArrayList<>();
    }

    @SneakyThrows
    public GameState clone() {

        var state = new GameState();
        state.setMap(this.getMap().clone());
        state.setGrass(new ArrayList<>(this.grass));
        state.setAnimals(new ArrayList<>(this.animals));

        return state;

//        TODO Разобраться, на методах ниже вылетал StackOverflow
//        GameState deepCopy = SerializationUtils.clone(this);
//        return deepCopy;

//        ObjectMapper objectMapper = new ObjectMapper();
//        String value = objectMapper.writeValueAsString(this);
//        GameState deepCopy = objectMapper.readValue(value, GameState.class);
//        return deepCopy;
    }
}
