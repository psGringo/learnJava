package com.example.main.alien.commands;

import com.example.main.alien.commands.common.CommandAnnotation;
import com.example.main.alien.commands.common.CommandExecutionResult;
import com.example.main.alien.commands.service.QuestionService;
import com.example.main.alien.state.UserGameState;
import java.util.ArrayList;
import java.util.List;
import org.openapi.alien.model.Option;

@CommandAnnotation(name = "enter_name")
public class EnterNameCommand extends CommandWithPayload {
    @Override
    public CommandExecutionResult execute() {

        String name = getPayLoad();
        getAppState().getGameState().getResults().put(name, new UserGameState(name));

        var response = getAppState().getStateMachineResponse();
        response.renderComponentName(NextQuestionCommand.getName(NextQuestionCommand.class));

        QuestionService.setNextQuestion();

        var nextCommandsList = getNewEmptyAwaitedCommandList();
        nextCommandsList.add(ExitCommand.class);
        nextCommandsList.add(NextQuestionCommand.class);

        return CommandExecutionResult.wait(nextCommandsList);
    }
}
