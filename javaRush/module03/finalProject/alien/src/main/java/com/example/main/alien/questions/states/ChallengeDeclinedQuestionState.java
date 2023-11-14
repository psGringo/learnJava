package com.example.main.alien.questions.states;

public class ChallengeDeclinedQuestionState extends QuestionState {
    public ChallengeDeclinedQuestionState() {
        question = "Ты отклонил вызов." + System.lineSeparator() + "Поражение";
        option = new org.openapi.alien.model.Option().value("Отклонить вызов");
    }
}
