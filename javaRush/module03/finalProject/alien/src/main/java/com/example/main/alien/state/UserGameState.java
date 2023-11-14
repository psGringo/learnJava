package com.example.main.alien.state;

import lombok.Getter;
import lombok.Setter;

public class UserGameState {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private boolean isWin;

    public UserGameState(String name) {
        this.name = name;
    }
}
