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

    public static void execute(String commandName, String payload) {
        Command command = getCommandByName(commandName);
        AppState appState = AppState.getInstance();
        var awaitedCommandClasses = appState.getAwaitedCommandClasses();
        if (awaitedCommandClasses.isEmpty()) {
            doExecute(command, payload);
        } else {
            Optional<Class<? extends Command>> awaitedCommandClass = awaitedCommandClasses.stream().filter(
                    aClass -> aClass.equals(command.getClass())
            ).findFirst();

            if (awaitedCommandClass.isPresent()) {
                doExecute(command, payload);
                appState.getAwaitedCommandClasses().clear();
            } else {
                String msg = "waiting for %s commands now but received %s";
                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < awaitedCommandClasses.size(); i++) {

                    var aClass = awaitedCommandClasses.get(i);

                    if (aClass.isAnnotationPresent(CommandAnnotation.class)) {
                        CommandAnnotation commandAnnotation = aClass.getAnnotation(CommandAnnotation.class);
                        if (i != awaitedCommandClasses.size() - 1) {
                            sb.append(commandAnnotation.name()).append(" | ").append(System.lineSeparator());
                        } else {
                            sb.append(commandAnnotation.name());
                        }
                    }
                }

                String formattedMsg = String.format(msg, sb, commandName);

                appState.getStateMachineResponse().error(formattedMsg);
            }
        }
    }

    private static void doExecute(Command command, String payload) {

        CommandExecutionResult commandExecutionResult = command.execute();
        var status = commandExecutionResult.getStatus();

        AppState appState = AppState.getInstance();
        appState.getCommandState().setCommand(command);
        appState.getCommandState().setPayload(payload);

        if (status.equals(ExecutionStatus.WAIT)) {
            appState.getAwaitedCommandClasses().addAll(commandExecutionResult.getAwaitedCommandClasses());
        } else if (status.equals(ExecutionStatus.DONE)) {
            appState.getAwaitedCommandClasses().clear();
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
