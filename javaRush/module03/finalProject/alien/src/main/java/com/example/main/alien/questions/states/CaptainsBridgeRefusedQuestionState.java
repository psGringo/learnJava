package com.example.main.alien.questions.states;

import org.openapi.alien.model.OptionUI;

public class CaptainsBridgeRefusedQuestionState extends QuestionState {
    public CaptainsBridgeRefusedQuestionState() {
        option = new OptionUI().value("Отказаться подниматься на мостик");
        question = "Ты не пошёл на переговоры" + System.lineSeparator() + "Поражение";
    }
}
