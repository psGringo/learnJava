package com.example.main.alien.questions.states;

import org.openapi.alien.model.OptionUI;

public class CaptainsBridgeQuestionState extends QuestionState {
    public CaptainsBridgeQuestionState() {
        option = new OptionUI().value("Подняться на мостик");
        question = "Ты поднялся на мостик" + System.lineSeparator() + "Ты кто ?";
        nextQuestions.add(FinalWinQuestionState.class);
        nextQuestions.add(FinalFailQuestionState.class);
    }
}
