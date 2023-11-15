package com.example.main.alien.state;

import java.util.HashMap;
import lombok.Data;
import lombok.Getter;

@Data
public class GameState {

    @Getter
    private HashMap<String, org.openapi.alien.model.GameResult> results;

    public GameState() {
        results = new HashMap<String, org.openapi.alien.model.GameResult>();
    }
}
