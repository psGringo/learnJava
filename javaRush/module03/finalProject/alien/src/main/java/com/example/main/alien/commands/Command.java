package com.example.main.alien.commands;

import com.example.main.alien.commands.common.CommandAnnotation;
import com.example.main.alien.commands.common.CommandExecutionResult;

public abstract class Command {

    public static String getName(Class<?> clazz) {

        if (clazz.isAnnotationPresent(CommandAnnotation.class)) {
            return clazz.getAnnotation(CommandAnnotation.class).name();
        }
        return "";
    }

    public abstract CommandExecutionResult execute();
}
