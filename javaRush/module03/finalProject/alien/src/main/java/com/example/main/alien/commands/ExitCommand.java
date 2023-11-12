package com.example.main.alien.commands;

import com.example.main.alien.commands.common.CommandAnnotation;
import com.example.main.alien.commands.common.CommandExecutionResult;

@CommandAnnotation(name = "exit")
public class ExitCommand extends Command {
    @Override
    public CommandExecutionResult execute() {
        return null;
    }
}
