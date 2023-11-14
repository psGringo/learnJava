package com.example.main.alien.questions.states;

public class ChallengeAcceptedQuestionState extends QuestionState {

    public ChallengeAcceptedQuestionState() {
        option = new org.openapi.alien.model.Option().value("Принять вызов");
        question = "Ты принял вызов." + System.lineSeparator() + "Поднимешься на мостик к капитану ?";
        nextQuestions.add(CaptainsBridgeQuestionState.class);
        nextQuestions.add(CaptainsBridgeRefusedQuestionState.class);
    }

}
