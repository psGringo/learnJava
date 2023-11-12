package com.example.main.alien.commands.service;

import com.example.main.alien.commands.AbstractCommand;
import com.example.main.alien.commands.CommandAnnotation;
import java.util.HashSet;
import java.util.Set;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

public class CommandService {


    public static AbstractCommand getCommandByName(String commandName) throws InstantiationException, IllegalAccessException {

        /*
         * Если команда есть в awaitedCommands
         * Если нет, пытаемся найти среди команд
         * */

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
    public static Set<Class<? extends AbstractCommand>> findAllClassesUsingReflectionsLibrary(String packageName) {
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        return new HashSet<>(reflections.getSubTypesOf(AbstractCommand.class));
    }
}
