package com.example.main.alien.commands;

import com.example.main.alien.commands.common.CommandAnnotation;
import com.example.main.alien.commands.common.CommandExecutionResult;
import com.example.main.alien.questions.services.QuestionService;
import com.example.main.alien.state.AppState;


@CommandAnnotation(name = "next_question")
public class NextQuestionCommand extends CommandWithPayload {
    @Override
    public CommandExecutionResult execute() {

        var response = getAppState().getStateMachineResponse();
        response.renderComponentName(NextQuestionCommand.getName(NextQuestionCommand.class));

        var option = QuestionService.getInstance().getSelectedOption(getPayLoad());
        AppState.getInstance().getQuestionState().setOption(option);


        QuestionService.getInstance().setNextQuestion();

        var nextCommandsList = getNewEmptyAwaitedCommandList();
        var isLastQuestion = getAppState().getQuestionState().getNextQuestions().isEmpty();
        nextCommandsList.add(ExitCommand.class);
        if (isLastQuestion) {
            nextCommandsList.add(FinishCommand.class);
        } else {
            nextCommandsList.add(NextQuestionCommand.class);
        }

        return CommandExecutionResult.wait(nextCommandsList);
    }
}
