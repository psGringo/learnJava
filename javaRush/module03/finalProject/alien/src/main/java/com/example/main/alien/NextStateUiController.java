package com.example.main.alien;

import com.example.main.alien.commands.service.CommandService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.openapi.alien.api.NextStateApi;
import org.openapi.alien.model.DirectionUI;
import org.openapi.alien.model.NextStateUI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class NextStateUiController implements NextStateApi {
    @SneakyThrows
    @Override
    public ResponseEntity<NextStateUI> nextState(String command, DirectionUI direction) {

        var cmd = CommandService.getCommandByName(command);
        cmd.execute();

        return null;
    }
//    @Override
//    public ResponseEntity<NextStateUI> nextState() {
//
//        AbstractCommand command;
//
//        if (AppState.getInstance().getCurrentCommand() != null) {
//            command = AppState.getInstance().getCurrentCommand();
//
//        } else {
//            // temporarily here
//            command = AppState.getInstance().getCurrentCommand();
//            // parse command from request
//            // execute
//            // TODO...
//        }
//
//        CommandExecutionResult commandExecutionResult = command.execute();
//        if (commandExecutionResult.getResult() == ExecutionResult.Next) {
//            AppState.getInstance().setCurrentCommand(commandExecutionResult.getNextCommand());
//        }
//
//        // change appState, maybe in command
//        // send state to ui
//
////        CommandExecutionResult.Ahead()
////        var nextStateUi = new NextStateUI();
//
//        return new ResponseEntity<>(null, HttpStatus.OK);
//    }




}
