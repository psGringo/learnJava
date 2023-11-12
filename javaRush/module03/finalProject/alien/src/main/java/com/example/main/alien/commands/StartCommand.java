package com.example.main.alien.commands;

import com.example.main.alien.state.AppState;

@CommandAnnotation(name = "start")
public class StartCommand extends AbstractCommand {

    @Override
    public CommandExecutionResult execute() {
        var appState = AppState.getInstance();

        var behaviour = new Class<?>[]{NextQuestionCommand.class, ExitCommand.class};
        appState.setBehaviour(behaviour);

        return null;
    }
}
