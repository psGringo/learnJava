package com.example.main.alien.state;

import com.example.main.alien.commands.Command;
import lombok.Getter;
import lombok.Setter;

public class CommandState {
    @Getter
    @Setter
    private Command command;

    @Getter
    @Setter
    private String payload;
}
