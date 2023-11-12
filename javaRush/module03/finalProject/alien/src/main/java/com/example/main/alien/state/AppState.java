package com.example.main.alien.state;

import com.example.main.alien.commands.Command;
import com.example.main.alien.questions.services.QuestionService;
import com.example.main.alien.questions.states.QuestionState;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


public class AppState {
    /**
     * Commands that possibly can be executed
     */
    @Getter
    private final List<Class<? extends Command>> awaitedCommandClasses;

    QuestionState questionState;

    private AppState() {
        questionState = QuestionService.getInstance().getNextQuestionState(null);
        awaitedCommandClasses = new ArrayList<>();
    }

    private static AppState appState;

    public static AppState getInstance() {
        if (appState == null)
            appState = new AppState();

        return appState;
    }
}
