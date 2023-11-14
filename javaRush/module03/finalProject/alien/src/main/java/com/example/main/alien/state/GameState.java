package com.example.main.alien.state;

import java.util.HashMap;
import lombok.Data;
import lombok.Getter;

@Data
public class GameState {

    @Getter
    private HashMap<String, UserGameState> results;

    public GameState() {
        results = new HashMap<String, UserGameState>();
    }
}