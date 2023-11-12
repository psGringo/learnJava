package com.example.main.alien.commands.questions.states;

import org.openapi.alien.model.OptionUI;

public class FinalFailQuestionState extends QuestionState {
    public FinalFailQuestionState() {
        option = new OptionUI().value("Солгать о себе");
        question = "Твою ложь разоблачили" + System.lineSeparator() + "Поражение";
    }
}
