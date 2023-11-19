package com.example.main.alien.commands;

import com.example.main.alien.commands.common.CommandAnnotation;
import com.example.main.alien.commands.common.CommandExecutionResult;

@CommandAnnotation(name = "finish")
public class FinishCommand extends CommandWithPayload {
    @Override
    public CommandExecutionResult execute() {

        var response = getAppState().getStateMachineResponse();
        response.renderComponentName(NextQuestionCommand.getName(FinishCommand.class));

        var nextCommandsList = getNewEmptyAwaitedCommandList();
        nextCommandsList.add(StartCommand.class);

        return CommandExecutionResult.wait(nextCommandsList);
    }
}
