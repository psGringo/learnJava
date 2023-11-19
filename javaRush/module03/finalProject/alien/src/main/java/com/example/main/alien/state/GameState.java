package com.example.main.alien.state;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import org.openapi.alien.model.GameResult;

@Data
public class GameState {

    private int count;

    @Getter
    private List<org.openapi.alien.model.GameResult> results;

    public void addGameResult(GameResult gameResult) {
        count++;
        gameResult.gameNumber(count);
        results.add(gameResult);
    }

    public GameState() {
        count = 0;
        results = new ArrayList<>();
    }
}
