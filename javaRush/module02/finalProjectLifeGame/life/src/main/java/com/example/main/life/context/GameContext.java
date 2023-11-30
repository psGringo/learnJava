package com.example.main.life.context;

import com.example.main.life.Statistics;
import com.example.main.life.models.GameState;
import com.example.main.observer.Observer;
import com.example.main.observer.ObserverEvent;
import java.util.concurrent.Executors;
import javax.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameContext {
    @Getter
    private GameState state;
    @Autowired
    AnimalsContext animalsContext;
    @Autowired
    GrassContext grassContext;
    Statistics statistics;

    Observer observer;
    @Autowired
    GameContextInitializer gameContextInitializer;

    public GameContext(GameContextInitializer gameContextInitializer) {
        this.gameContextInitializer = gameContextInitializer;
        statistics = Statistics.getInstance();
        observer = new Observer();
        observer.subscribe(ObserverEvent.ChangeState, statistics);

    }

    public GameState getNextState() {
        if (state == null) {
            state = this.gameContextInitializer.testWolfAndSheep();
        } else {
            var stateClone = state.clone();
            grassContext.setNextState(stateClone, state);
            animalsContext.setNextState(stateClone, state);
            observer.notify(ObserverEvent.ChangeState, state);
        }

        return state;
    }
}
