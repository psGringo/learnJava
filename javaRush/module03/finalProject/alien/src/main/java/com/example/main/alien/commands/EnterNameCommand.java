package com.example.main.alien.commands;

import com.example.main.alien.commands.common.CommandAnnotation;
import com.example.main.alien.commands.common.CommandExecutionResult;
import com.example.main.alien.questions.services.QuestionService;
import org.openapi.alien.model.GameResult;

@CommandAnnotation(name = "enter_name")
public class EnterNameCommand extends CommandWithPayload {
    @Override
    public CommandExecutionResult execute() {

        String name = getPayLoad();
        getAppState().setUserName(name);

        var response = getAppState().getStateMachineResponse();
        response.renderComponentName(NextQuestionCommand.getName(NextQuestionCommand.class));

        QuestionService.getInstance().setNextQuestion();

        var nextCommandsList = getNewEmptyAwaitedCommandList();
        nextCommandsList.add(ExitCommand.class);
        nextCommandsList.add(NextQuestionCommand.class);

        return CommandExecutionResult.wait(nextCommandsList);
    }
}
