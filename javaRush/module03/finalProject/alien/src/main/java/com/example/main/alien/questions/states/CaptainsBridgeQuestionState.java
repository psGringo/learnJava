package com.example.main.alien.questions.states;

public class CaptainsBridgeQuestionState extends QuestionState {
    public CaptainsBridgeQuestionState() {
        option = new org.openapi.alien.model.Option().value("Подняться на мостик");
        question = "Ты поднялся на мостик" + System.lineSeparator() + "Ты кто ?";
        nextQuestions.add(FinalWinQuestionState.class);
        nextQuestions.add(FinalFailQuestionState.class);
    }
}
