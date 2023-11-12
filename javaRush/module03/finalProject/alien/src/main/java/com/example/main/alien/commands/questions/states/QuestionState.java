package com.example.main.alien.commands.questions.states;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class QuestionState {
    protected String question;
    @Getter
    protected org.openapi.alien.model.OptionUI option;
    @Getter
    protected List<Class<? extends QuestionState>> nextQuestions;



    public QuestionState() {
        nextQuestions = new ArrayList<>();
    }
}
