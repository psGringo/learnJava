package com.example.main.alien.commands;

import com.example.main.alien.commands.common.CommandAnnotation;
import com.example.main.alien.commands.common.CommandExecutionResult;
import com.example.main.alien.commands.service.QuestionService;
import java.util.ArrayList;
import java.util.List;
import org.openapi.alien.model.Question;

@CommandAnnotation(name = "next_question")
public class NextQuestionCommand extends Command {
    @Override
    public CommandExecutionResult execute() {

        var response = getAppState().getStateMachineResponse();
        response.renderComponentName(NextQuestionCommand.getName(NextQuestionCommand.class));

        QuestionService.setNextQuestion();

        var nextCommandsList = getNewEmptyAwaitedCommandList();
        nextCommandsList.add(NextQuestionCommand.class);
        nextCommandsList.add(ExitCommand.class);

        return CommandExecutionResult.wait(nextCommandsList);
    }
}
