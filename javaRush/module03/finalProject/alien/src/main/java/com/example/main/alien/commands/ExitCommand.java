package com.example.main.alien.commands;

import com.example.main.alien.commands.common.CommandAnnotation;
import com.example.main.alien.commands.common.CommandExecutionResult;
import com.example.main.alien.state.AppState;

@CommandAnnotation(name = "exit")
public class ExitCommand extends Command {
    @Override
    public CommandExecutionResult execute() {

        getAppState().clear();

        AppState.getInstance().getStateMachineResponse().renderComponentName("start");

        return CommandExecutionResult.done();
    }
}
