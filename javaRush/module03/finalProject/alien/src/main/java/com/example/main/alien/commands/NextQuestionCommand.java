package com.example.main.alien.commands;

import com.example.main.alien.commands.common.CommandAnnotation;
import com.example.main.alien.commands.common.CommandExecutionResult;
import org.openapi.alien.model.Question;

@CommandAnnotation(name = "next_question")
public class NextQuestionCommand extends Command {
    @Override
    public CommandExecutionResult execute() {

        var response = getAppState().getStateMachineResponse();

        response.renderComponentName(NextQuestionCommand.getName(NextQuestionCommand.class));
        var questionState = getAppState().getQuestionState();

        org.openapi.alien.model.Question question = new Question();
        question.setText(questionState.getQuestion());

        questionState.getNextQuestions().forEach(aClass -> {
            try {
                var nextQuestion = aClass.newInstance();
                question.getOptions().add(nextQuestion.getOption());
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });

        response.question(question);

        var nextCommandsList = getNewEmptyAwaitedCommandList();
        nextCommandsList.add(NextQuestionCommand.class);
        nextCommandsList.add(ExitCommand.class);

        return CommandExecutionResult.wait(nextCommandsList);
    }
}
