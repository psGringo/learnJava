package com.example.main.alien.commands.common;

import com.example.main.alien.commands.Command;
import com.example.main.alien.commands.service.ExecutionStatus;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.SneakyThrows;

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
    public static CommandExecutionResult wait(List<Class<? extends Command>> newWaitedCommandClasses) {

        List<Class<? extends Command>> awaitedCommandClasses = new ArrayList<>();

        newWaitedCommandClasses.forEach(aClass -> {
            if (aClass.isAnnotationPresent(CommandAnnotation.class)) {
                awaitedCommandClasses.add(aClass);
            } else {
                String msg = "CommandAnnotation not found at command %s";
                throw new IllegalArgumentException(String.format(msg, aClass.getName()));
            }
        });

        return new CommandExecutionResult(ExecutionStatus.NeedAddAwaitedCommands, awaitedCommandClasses);
    }

    public static CommandExecutionResult done() {
        return new CommandExecutionResult(ExecutionStatus.Done);
    }
}


