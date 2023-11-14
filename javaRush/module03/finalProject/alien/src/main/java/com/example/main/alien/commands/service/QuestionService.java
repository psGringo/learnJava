package com.example.main.alien.commands.service;

import com.example.main.alien.commands.NextQuestionCommand;
import com.example.main.alien.state.AppState;
import java.util.ArrayList;
import java.util.List;

public class QuestionService {
    public static void setNextQuestion(){
        var response = AppState.getInstance().getStateMachineResponse();


        var questionState = AppState.getInstance().getQuestionState();
        org.openapi.alien.model.Question question = new org.openapi.alien.model.Question();
        question.setText(questionState.getQuestion());

        questionState.getNextQuestions().forEach(aClass -> {
            try {
                var nextQuestion = aClass.newInstance();
                List<org.openapi.alien.model.Option> options = new ArrayList<>();
                options.add(nextQuestion.getOption());
                question.setOptions(options);
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });

        response.question(question);
    }
}
