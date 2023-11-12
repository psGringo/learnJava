package com.example.main.alien.commands;

import com.example.main.alien.commands.common.CommandAnnotation;
import com.example.main.alien.commands.common.CommandExecutionResult;

@CommandAnnotation(name = "next_question")
public class NextQuestionCommand extends Command {
    @Override
    public CommandExecutionResult execute() {

        // getNextQuestion

        return null;
    }
}
