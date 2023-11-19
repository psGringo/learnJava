package com.example.main.alien.questions.states;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class QuestionState {
    @Getter
    protected String question;
    @Getter
    @Setter
    protected org.openapi.alien.model.Option option;
    @Getter
    protected List<Class<? extends QuestionState>> nextQuestions;
    @Getter
    protected boolean isVictoryState;

    public QuestionState() {
        nextQuestions = new ArrayList<>();
        isVictoryState = false;
    }
}