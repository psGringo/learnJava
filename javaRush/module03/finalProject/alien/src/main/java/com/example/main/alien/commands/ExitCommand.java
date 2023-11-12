package com.example.main.alien.commands;

@CommandAnnotation(name = "exit")
public class ExitCommand extends AbstractCommand {
    @Override
    public CommandExecutionResult execute() {
        return null;
    }
}
