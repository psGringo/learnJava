package com.example.main.alien.questions.states;

public class InitialQuestionState extends QuestionState {
    public InitialQuestionState() {
        question = "Ты потерял память." + System.lineSeparator() + "Принять вызов НЛО ?";
        nextQuestions.add(ChallengeAcceptedQuestionState.class);
        nextQuestions.add(ChallengeDeclinedQuestionState.class);
    }
}

