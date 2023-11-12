package com.example.main.alien.questions.states;

public class InitialQuestionState extends QuestionState {
    public InitialQuestionState() {
        question = "Ты принял вызов." + System.lineSeparator() + "Поднимешься на мостик к капитану ?";
        nextQuestions.add(ChallengeAcceptedQuestionState.class);
        nextQuestions.add(ChallengeDeclinedQuestionState.class);
    }
}

