package com.example.main.alien.questions.states;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class QuestionState {
    @Getter
    protected String question;
    @Getter
    protected org.openapi.alien.model.Option option;
    @Getter
    protected List<Class<? extends QuestionState>> nextQuestions;

    public QuestionState() {
        nextQuestions = new ArrayList<>();
    }
}
