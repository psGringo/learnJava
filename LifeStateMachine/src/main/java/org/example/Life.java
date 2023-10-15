package org.example;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import org.example.animals.Animal;
import org.example.animals.SheepFactory;
import org.example.animals.WolfFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class Life {


    private List<Animal> animals;

    private static Life life;

    private Life() {
        printBorders();
        Terminal.getInstance().getLaternaTerminal().addResizeListener((terminal, newSize) -> {
            printBorders();
        });
    }

    void createAnimals() {
        animals = new ArrayList<>();
        animals.add(new WolfFactory().create());
        animals.add(new WolfFactory().create());
        animals.add(new SheepFactory().create());
    }

    public static Life getInstance() {
        if (life == null) {
            life = new Life();
        }

        return life;
    }

    public void execute() {
        createAnimals();
        var executors = Executors.newScheduledThreadPool(10);

        while (true) {
            try {
                animals.forEach(animal -> {
                    executors.submit(animal);
                });
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void printBorders() {
        try {
            int countCols = AppProps.getInt(Constants.screenSizeX);
            int countRows = AppProps.getInt(Constants.screenSizeY);

            var tg = Terminal.getInstance().getLaternaTerminal().newTextGraphics();
            tg.drawRectangle(new TerminalPosition(0, 0), new TerminalSize(countCols, countRows), '*');
            Terminal.getInstance().getLaternaScreen().refresh(com.googlecode.lanterna.screen.Screen.RefreshType.DELTA);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
