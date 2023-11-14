package com.example.main.alien.questions.states;

public class CaptainsBridgeRefusedQuestionState extends QuestionState {
    public CaptainsBridgeRefusedQuestionState() {
        option = new org.openapi.alien.model.Option().value("Отказаться подниматься на мостик");
        question = "Ты не пошёл на переговоры" + System.lineSeparator() + "Поражение";
    }
}
