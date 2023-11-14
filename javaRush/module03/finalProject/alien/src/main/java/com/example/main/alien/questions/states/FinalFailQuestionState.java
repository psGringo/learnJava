package com.example.main.alien.questions.states;

public class FinalFailQuestionState extends QuestionState {
    public FinalFailQuestionState() {
        option = new org.openapi.alien.model.Option().value("Солгать о себе");
        question = "Твою ложь разоблачили" + System.lineSeparator() + "Поражение";
    }
}
