package com.example.main.alien.commands;

import com.example.main.alien.commands.common.CommandAnnotation;
import com.example.main.alien.commands.common.CommandExecutionResult;
import com.example.main.alien.state.AppState;

@CommandAnnotation(name = "start")
public class StartCommand extends Command {
    @Override
    public CommandExecutionResult execute() {
        getAppState().clear();
        var response = AppState.getInstance().getStateMachineResponse();
        response.message("please enter name");
        response.renderComponentName(EnterNameCommand.getName(EnterNameCommand.class));

        var nextCommandsList = getNewEmptyAwaitedCommandList();
        nextCommandsList.add(ExitCommand.class);
        nextCommandsList.add(EnterNameCommand.class);

        return CommandExecutionResult.wait(nextCommandsList);
    }
}
