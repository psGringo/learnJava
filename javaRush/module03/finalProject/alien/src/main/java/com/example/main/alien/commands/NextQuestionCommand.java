package com.example.main.alien.commands;

import com.example.main.alien.commands.common.CommandAnnotation;
import com.example.main.alien.commands.common.CommandExecutionResult;
import com.example.main.alien.questions.services.QuestionService;
import com.example.main.alien.state.AppState;
import java.util.stream.Collectors;
import org.openapi.alien.model.GameResult;

@CommandAnnotation(name = "next_question")
public class NextQuestionCommand extends CommandWithPayload {
    @Override
    public CommandExecutionResult execute() {

        var response = getAppState().getStateMachineResponse();
        response.renderComponentName(NextQuestionCommand.getName(NextQuestionCommand.class));

        var option = QuestionService.getInstance().getSelectedOption(getPayLoad());
        AppState.getInstance().getQuestionState().setOption(option);

        QuestionService.getInstance().setNextQuestion();

        var nextCommandsList = getNewEmptyAwaitedCommandList();
        var isLastQuestion = getAppState().getQuestionState().getNextQuestions().isEmpty();
        nextCommandsList.add(ExitCommand.class);
        if (isLastQuestion) {

            var userName = getAppState().getUserName();
            var isVictory = getAppState().getQuestionState().isVictoryState();

            GameResult gameResult = new GameResult();
            gameResult.playerName(userName);
            gameResult.isVictory(isVictory);
            getAppState().getGameState().addGameResult(gameResult);

            var gameResultList = getAppState().getGameState().getResults();
            response.gameResults(gameResultList);

            nextCommandsList.add(FinishCommand.class);
        } else {
            nextCommandsList.add(NextQuestionCommand.class);
        }

        return CommandExecutionResult.wait(nextCommandsList);
    }
}
