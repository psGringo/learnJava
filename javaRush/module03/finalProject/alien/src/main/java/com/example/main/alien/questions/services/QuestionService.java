package com.example.main.alien.questions.services;

import com.example.main.alien.questions.states.InitialQuestionState;
import com.example.main.alien.questions.states.QuestionState;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;

public class QuestionService {

    private static QuestionService questionService;

    private QuestionService() {
    }

    public static QuestionService getInstance() {
        if (questionService == null)
            questionService = new QuestionService();

        return questionService;
    }

    private QuestionState currentQuestionState;

    public void clear() {
        currentQuestionState = null;
    }

    @SneakyThrows
    public QuestionState getNextQuestionState(org.openapi.alien.model.Option option) {
        if (currentQuestionState == null) {
            currentQuestionState = InitialQuestionState.class.newInstance();
            return currentQuestionState;
        }

        List<QuestionState> nextStates = new ArrayList<>();
        currentQuestionState.getNextQuestions().forEach(aClass -> {
            try {
                nextStates.add(aClass.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });

        if (nextStates.isEmpty()) {
            return null;
        }

        var nextQuestionState = nextStates.stream().filter(
                (questionState) -> questionState.getOption().getValue().equals(option.getValue())
        ).findFirst();

        if (nextQuestionState.isPresent()) {
            throw new IllegalArgumentException("smth wrong with next question states");
        } else

            return nextQuestionState.get();
    }
}
