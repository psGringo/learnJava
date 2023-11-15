package com.example.main.alien.state;

import com.example.main.alien.commands.Command;
import com.example.main.alien.questions.services.QuestionService;
import com.example.main.alien.questions.states.QuestionState;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.openapi.alien.model.StateMachineResponse;

public class AppState {

    /**
     * Last received command
     */
    @Getter
    CommandState commandState;

    @Getter
    GameState gameState;

    @Getter
    private List<Class<? extends Command>> awaitedCommandClasses;

    @Getter
    org.openapi.alien.model.StateMachineResponse stateMachineResponse;

    public void clear() {
        QuestionService.getInstance().clear();
        awaitedCommandClasses.clear();
        stateMachineResponse = null;
        commandState = null;

        questionState = QuestionService.getInstance().getNextQuestionState(null);
        awaitedCommandClasses = new ArrayList<>();
        stateMachineResponse = new StateMachineResponse();
        commandState = new CommandState();
    }

    @Getter
    @Setter
    QuestionState questionState;

    private AppState() {
        awaitedCommandClasses = new ArrayList<>();
        stateMachineResponse = new StateMachineResponse();
        commandState = new CommandState();
        gameState = new GameState();
    }

    private static AppState appState;

    public static AppState getInstance() {
        if (appState == null)
            appState = new AppState();

        return appState;
    }
}
