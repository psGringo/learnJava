package org.example;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try {
            var terminal = new DefaultTerminalFactory().createTerminal();
//            terminal.setCursorPosition(5, 5);
//            terminal.putCharacter('S');
//            terminal.flush();

            var screen = new TerminalScreen(terminal);
            screen.startScreen();
            var textGraphics = screen.newTextGraphics();
            textGraphics.putString(5, 5, "hi, there");

            textGraphics.drawRectangle(new TerminalPosition(10, 10), new TerminalSize(10, 10), 's');


            screen.refresh();
            screen.readInput();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        while (true) {
        }

    }
}