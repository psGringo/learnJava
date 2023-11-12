package com.example.main.alien.commands.service;

import com.example.main.alien.commands.Command;
import com.example.main.alien.commands.common.CommandAnnotation;
import com.example.main.alien.commands.common.CommandExecutionResult;
import com.example.main.alien.state.AppState;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import lombok.SneakyThrows;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

public class CommandService {

    private static void execute(String commandName) {
        Command command = getCommandByName(commandName);
        AppState appState = AppState.getInstance();
        var awaitedCommandClasses = appState.getAwaitedCommandClasses();
        if (awaitedCommandClasses.isEmpty()) {
            doExecute(command);
        } else {
            Optional<Class<? extends Command>> awaitedCommandClass = awaitedCommandClasses.stream().filter(
                    aClass -> aClass.equals(command.getClass())
            ).findFirst();

            if (awaitedCommandClass.isPresent()) {
                doExecute(command);
                appState.getAwaitedCommandClasses().clear();
            } else {
                String msg = "waiting for %s commands now but received %s";
                StringBuilder sb = new StringBuilder();
                awaitedCommandClasses.forEach(aClass -> {
                    sb.append(aClass.getName());
                });
                throw new IllegalArgumentException(String.format(msg, sb, commandName));
            }
        }
    }

    private static void doExecute(Command command) {
        CommandExecutionResult commandExecutionResult = command.execute();
        var status = commandExecutionResult.getStatus();
        AppState appState = AppState.getInstance();
        if (status.equals(ExecutionStatus.NeedAddAwaitedCommands)) {
            appState.getAwaitedCommandClasses().addAll(commandExecutionResult.getAwaitedCommandClasses());
        }
    }

    @SneakyThrows
    public static Command getCommandByName(String commandName) {

        var classes = findAllClassesUsingReflectionsLibrary("com.example.main.alien.commands");
        var commandClass = classes.stream().filter((c) -> {
            if (c.isAnnotationPresent(CommandAnnotation.class)) {
                CommandAnnotation commandAnnotation = c.getAnnotation(CommandAnnotation.class);
                return commandAnnotation.name().equals(commandName);
            }
            return false;
        }).findFirst();

        if (commandClass.isPresent()) {
            return commandClass.get().newInstance();
        }

        throw new IllegalArgumentException(String.format("command %s not found in classes", commandName));
    }


    @SuppressWarnings("deprecation")
    private static Set<Class<? extends Command>> findAllClassesUsingReflectionsLibrary(String packageName) {
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        return new HashSet<>(reflections.getSubTypesOf(Command.class));
    }
}
