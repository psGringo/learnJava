package com.example.main.alien.state;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class AppStateTest {

    @Test
    public void getInstance() {
        var obj = AppState.getInstance();
        Assertions.assertNotNull(obj);
    }
}