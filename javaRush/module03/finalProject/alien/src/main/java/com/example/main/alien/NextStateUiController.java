package com.example.main.alien;

import com.example.main.alien.commands.service.CommandService;
import com.example.main.alien.state.AppState;
import lombok.AllArgsConstructor;
import org.openapi.alien.api.NextStateApi;
import org.openapi.alien.model.NextStateUI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class NextStateUiController implements NextStateApi {

    @Override
    public ResponseEntity<NextStateUI> nextState(String command) {
        CommandService.execute(command);
        var nextStateUI = new NextStateUI();
        nextStateUI.setStateMachineResponse(AppState.getInstance().getStateMachineResponseUI());
        return new ResponseEntity<>(nextStateUI, HttpStatus.OK);
    }
}
