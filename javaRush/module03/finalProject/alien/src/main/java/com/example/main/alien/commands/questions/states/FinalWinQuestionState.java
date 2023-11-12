package com.example.main.alien.commands.questions.states;

import org.openapi.alien.model.OptionUI;

public class FinalWinQuestionState extends QuestionState {
    public FinalWinQuestionState() {
        option = new OptionUI().value("Рассказать правду о себе");
        question = "Тебя вернули домой" + System.lineSeparator() + "Победа";
    }
}
