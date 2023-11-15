package com.example.main.alien.questions.services;

import com.example.main.alien.questions.states.InitialQuestionState;
import com.example.main.alien.questions.states.QuestionState;
import com.example.main.alien.state.AppState;
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
        if (currentQuestionState == null || option == null) {
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

        if (!nextQuestionState.isPresent()) {
            throw new IllegalArgumentException("smth wrong with next question states");
        } else {
            currentQuestionState = nextQuestionState.get();
            return nextQuestionState.get();
        }

    }

    public void setNextQuestion() {
        var appState = AppState.getInstance();
        // To think. With this var below it always null on first iteration, why ?
        var questionState = AppState.getInstance().getQuestionState();

        QuestionState nextQuestionState = null;
        if (AppState.getInstance().getQuestionState() == null) {
            nextQuestionState = QuestionService.getInstance().getNextQuestionState(null);
            appState.setQuestionState(nextQuestionState);
        } else {
            var selectedOption = AppState.getInstance().getQuestionState().getOption();
            nextQuestionState = QuestionService.getInstance().getNextQuestionState(selectedOption);
            appState.setQuestionState(nextQuestionState);
        }

        // preparing response
        org.openapi.alien.model.Question question = new org.openapi.alien.model.Question();
        question.setText(AppState.getInstance().getQuestionState().getQuestion());
        question.setOptions(getCurrentQuestionOptions());

        appState.getStateMachineResponse().question(question);
    }

    public List<org.openapi.alien.model.Option> getCurrentQuestionOptions() {
        List<org.openapi.alien.model.Option> options = new ArrayList<>();
        AppState.getInstance().getQuestionState().getNextQuestions().forEach(aClass -> {
            try {
                var nextQuestion = aClass.newInstance();
                options.add(nextQuestion.getOption());
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });

        return options;
    }

    @SneakyThrows
    public org.openapi.alien.model.Option getSelectedOption(String selectedOption) {

        for (int i = 0; i < AppState.getInstance().getQuestionState().getNextQuestions().size(); i++) {
            var aClass = AppState.getInstance().getQuestionState().getNextQuestions().get(i);
            var nextQuestion = aClass.newInstance();
            if (nextQuestion.getOption().getValue().equals(selectedOption)) {
                return nextQuestion.getOption();
            }
        }

        return null;
    }
}
