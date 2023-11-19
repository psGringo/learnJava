package com.example.main.alien.commands;

import com.example.main.alien.commands.common.CommandAnnotation;
import com.example.main.alien.commands.common.CommandExecutionResult;
import com.example.main.alien.state.AppState;

@CommandAnnotation(name = "exit")
public class ExitCommand extends Command {
    @Override
    public CommandExecutionResult execute() {

        getAppState().clear();
        var response = AppState.getInstance().getStateMachineResponse();

        response.renderComponentName(StartCommand.getName(StartCommand.class));
        var gameResultList = getAppState().getGameState().getResults();
        response.gameResults(gameResultList);

        return CommandExecutionResult.done();
    }
}
