package com.example.main.alien.commands;

import com.example.main.alien.commands.common.CommandAnnotation;
import com.example.main.alien.commands.common.CommandExecutionResult;
import com.example.main.alien.state.UserGameState;

@CommandAnnotation(name = "enter_name")
public class EnterNameCommand extends CommandWithPayload {
    @Override
    public CommandExecutionResult execute() {

        String name = getPayLoad();
        getAppState().getGameState().getResults().put(name, new UserGameState(name));

        var nextCommandsList = getNewEmptyAwaitedCommandList();
        nextCommandsList.add(NextQuestionCommand.class);
        nextCommandsList.add(ExitCommand.class);

        return CommandExecutionResult.wait(nextCommandsList);
    }
}
