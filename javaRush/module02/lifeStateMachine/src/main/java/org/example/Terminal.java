package org.example;

import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import lombok.Getter;

import java.io.IOException;

public class Terminal {
    @Getter
    private com.googlecode.lanterna.terminal.Terminal laternaTerminal;
    @Getter
    private TerminalScreen laternaScreen;

    private static Terminal terminal;

    private Terminal() {
        try {
            laternaTerminal = new DefaultTerminalFactory().createTerminal();
            laternaScreen = new TerminalScreen(laternaTerminal);
            laternaScreen.startScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Terminal getInstance() {
        if (terminal == null) {
            terminal = new Terminal();
        }
        return terminal;
    }
}
