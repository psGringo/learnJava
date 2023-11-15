package com.example.main.alien.commands;

import com.example.main.alien.commands.common.CommandAnnotation;
import com.example.main.alien.commands.common.CommandExecutionResult;
import com.example.main.alien.questions.services.QuestionService;
import org.openapi.alien.model.GameResult;

@CommandAnnotation(name = "finish")
public class FinishCommand extends CommandWithPayload {
    @Override
    public CommandExecutionResult execute() {

        String name = getPayLoad();
        getAppState().getGameState().getResults().put(name, new GameResult());
        getAppState().clear();

        var response = getAppState().getStateMachineResponse();
        response.renderComponentName(NextQuestionCommand.getName(FinishCommand.class));

        QuestionService.getInstance().setNextQuestion();
        var nextCommandsList = getNewEmptyAwaitedCommandList();
        nextCommandsList.add(StartCommand.class);

        return CommandExecutionResult.wait(nextCommandsList);
    }
}
