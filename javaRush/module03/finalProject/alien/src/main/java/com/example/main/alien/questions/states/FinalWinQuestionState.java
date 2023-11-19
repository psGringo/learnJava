package com.example.main.alien.questions.states;

public class FinalWinQuestionState extends QuestionState {
    public FinalWinQuestionState() {
        option = new org.openapi.alien.model.Option().value("Рассказать правду о себе");
        question = "Тебя вернули домой" + System.lineSeparator() + "Победа";
        isVictoryState = true;
    }
}
