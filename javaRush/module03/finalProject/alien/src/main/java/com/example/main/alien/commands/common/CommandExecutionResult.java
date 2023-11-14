package com.example.main.alien.commands.common;

import com.example.main.alien.commands.Command;
import com.example.main.alien.commands.CommandWithPayload;
import com.example.main.alien.commands.service.ExecutionStatus;
import com.example.main.alien.state.AppState;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.SneakyThrows;
import org.openapi.alien.model.CommandDescription;

public class CommandExecutionResult {
    @Getter
    private ExecutionStatus status;

    @Getter
    private List<Class<? extends Command>> awaitedCommandClasses;

    public CommandExecutionResult(ExecutionStatus executionResult, List<Class<? extends Command>> awaitedCommandClasses) {
        this.status = executionResult;
        this.awaitedCommandClasses = awaitedCommandClasses;
    }

    public CommandExecutionResult(ExecutionStatus executionResult) {
        this.status = executionResult;
        this.awaitedCommandClasses = null;
    }

    @SneakyThrows
    public static CommandExecutionResult wait(List<Class<? extends Command>> newAwaitedCommandClasses) {

        List<Class<? extends Command>> awaitedCommandClasses = new ArrayList<>();
        List<org.openapi.alien.model.CommandDescription> nextCommandDescriptions = new ArrayList<>();
        var appState = AppState.getInstance();

        newAwaitedCommandClasses.forEach(aClass -> {
            if (aClass.isAnnotationPresent(CommandAnnotation.class)) {
                awaitedCommandClasses.add(aClass);
                var commandAnnotation = aClass.getAnnotation(CommandAnnotation.class);

                var commandDescription = new CommandDescription();
                commandDescription.name(commandAnnotation.name());
                commandDescription.needPayload(CommandWithPayload.class.isAssignableFrom(aClass));
                nextCommandDescriptions.add(commandDescription);

            } else {
                String msg = "CommandAnnotation not found at command %s";
                throw new IllegalArgumentException(String.format(msg, aClass.getName()));
            }
        });

        appState.getStateMachineResponse().nextCommands(nextCommandDescriptions);

        return new CommandExecutionResult(ExecutionStatus.WAIT, awaitedCommandClasses);
    }

    public static CommandExecutionResult done() {
        return new CommandExecutionResult(ExecutionStatus.DONE);
    }
}


