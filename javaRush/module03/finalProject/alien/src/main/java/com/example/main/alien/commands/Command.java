package com.example.main.alien.commands;

import com.example.main.alien.commands.common.CommandAnnotation;
import com.example.main.alien.commands.common.CommandExecutionResult;
import com.example.main.alien.state.AppState;
import java.util.ArrayList;
import java.util.List;

public abstract class Command {

    String getPayLoad() {
        var appState = AppState.getInstance();
        return appState.getCommandState().getPayload();
    }

    AppState getAppState() {
        return AppState.getInstance();
    }

    public static String getName(Class<? extends Command> commandClass) {

        if (commandClass.isAnnotationPresent(CommandAnnotation.class)) {
            return commandClass.getAnnotation(CommandAnnotation.class).name();
        } else {
            throw new IllegalArgumentException("lost command annotation");
        }
    }

    public static List<Class<? extends Command>> getNewEmptyAwaitedCommandList() {
        return new ArrayList<Class<? extends Command>>();
    }


    public abstract CommandExecutionResult execute();
}
