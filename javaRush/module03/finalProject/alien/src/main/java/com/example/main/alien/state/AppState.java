package com.example.main.alien.state;

import com.example.main.alien.commands.Command;
import com.example.main.alien.questions.services.QuestionService;
import com.example.main.alien.questions.states.QuestionState;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.openapi.alien.model.StateMachineResponseUI;

public class AppState {
    /**
     * Commands that possibly can be executed
     */
    @Getter
    private final List<Class<? extends Command>> awaitedCommandClasses;

    @Getter
    StateMachineResponseUI stateMachineResponseUI;

    @Getter
    QuestionState questionState;

    private AppState() {
        questionState = QuestionService.getInstance().getNextQuestionState(null);
        awaitedCommandClasses = new ArrayList<>();
        stateMachineResponseUI = new StateMachineResponseUI();
    }

    private static AppState appState;

    public static AppState getInstance() {
        if (appState == null)
            appState = new AppState();

        return appState;
    }
}
