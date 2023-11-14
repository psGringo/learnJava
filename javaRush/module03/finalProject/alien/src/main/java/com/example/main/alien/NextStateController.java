package com.example.main.alien;

import com.example.main.alien.commands.service.CommandService;
import com.example.main.alien.state.AppState;
import lombok.AllArgsConstructor;
import org.openapi.alien.api.NextStateApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.openapi.alien.model.NextState;

@RestController
@AllArgsConstructor
public class NextStateController implements NextStateApi {

    @Override
    public ResponseEntity<NextState> nextState(String command, String payload) {
        CommandService.execute(command, payload);
        var nextState = new org.openapi.alien.model.NextState();
        nextState.setStateMachineResponse(AppState.getInstance().getStateMachineResponse());
        return new ResponseEntity<>(nextState, HttpStatus.OK);
    }
}
