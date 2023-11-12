package com.example.main.alien.commands.questions.states;

import org.openapi.alien.model.OptionUI;

public class ChallengeDeclinedQuestionState extends QuestionState {
    public ChallengeDeclinedQuestionState() {
        question = "Ты отклонил вызов." + System.lineSeparator() + "Поражение";
        option = new OptionUI().value("Отклонить вызов");
    }
}
