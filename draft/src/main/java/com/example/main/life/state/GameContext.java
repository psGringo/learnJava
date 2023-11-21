package com.example.main.life.state;

import com.example.main.life.models.GameState;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameContext {
    private GameState initialState;
    @Getter
    private GameState state;
    @Autowired
    AnimalsContext animalsContext;
    @Autowired
    GrassContext grassContext;
    @Autowired
    GameContextInitializer gameContextInitializer;

    public GameContext() {
        initialState = gameContextInitializer.testOneAnimal();
        state = initialState.getClone();
    }

    private GameState getNextState() {
        var stateClone = state.getClone();
        grassContext.setNextState(stateClone, state);
        animalsContext.setNextState(stateClone, state);

        return state;
    }

}
