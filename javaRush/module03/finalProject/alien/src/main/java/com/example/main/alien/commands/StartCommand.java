package com.example.main.alien.commands;

import com.example.main.alien.commands.common.CommandAnnotation;
import com.example.main.alien.commands.common.CommandExecutionResult;
import com.example.main.alien.state.AppState;

@CommandAnnotation(name = "start")
public class StartCommand extends Command {

    @Override
    public CommandExecutionResult execute() {
        AppState.getInstance().getStateMachineResponseUI().questionMode(false);
        AppState.getInstance().getStateMachineResponseUI().value("command start successfully executed");
        return CommandExecutionResult.done();
    }
}
