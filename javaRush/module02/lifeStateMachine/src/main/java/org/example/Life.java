package org.example;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import org.example.animals.Animal;
import org.example.animals.AnimalType;
import org.example.animals.SheepFactory;
import org.example.animals.WolfFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;

public class Life {

    private List<Animal> animals;

    private static Life life;

    private HashMap<Animal, Position> herbsDict;

    private HashMap<Position, Animal> predatorsDict;

    private List<Animal> herbs;
    private List<Animal> predators;

    private Life() {
        printBorders();
        Terminal.getInstance().getLaternaTerminal().addResizeListener((terminal, newSize) -> {
            printBorders();
        });

        herbsDict = new HashMap<>();
        predatorsDict = new HashMap<>();
    }

    void createAnimals() {
        animals = new ArrayList<>();
        animals.add(new WolfFactory().create());
        animals.add(new WolfFactory().create());
        animals.add(new SheepFactory().create());
        animals.add(new SheepFactory().create());
        animals.add(new SheepFactory().create());
        animals.add(new SheepFactory().create());

        herbs = animals.stream().filter((a) -> a.getAnimalType().equals(AnimalType.Herbivores)).toList();

        predators = animals.stream().filter((a) -> a.getAnimalType().equals(AnimalType.Predator)).toList();

//        predators.forEach((p) -> {
//            Position position = p.getPosition();
//            predatorsDict.put(position, p);
//
//        });
    }

    public static Life getInstance() {
        if (life == null) {
            life = new Life();
        }

        return life;
    }

    boolean isIntersects(Animal a, Animal b) {
        return (

                (a.getPosition().X.getStart() <= b.getPosition().X.getStart()) &&
                        (a.getPosition().Y.getStart() <= b.getPosition().Y.getStart()) &&
                        (a.getPosition().Y.getEnd() >= b.getPosition().Y.getStart()) &&
                        (a.getPosition().X.getEnd() >= b.getPosition().X.getStart())

        );
    }

    public void execute() {
        createAnimals();
        var executors = Executors.newCachedThreadPool();

        while (true) {
            try {
                animals.forEach(animal -> {
                    executors.submit(animal);
                });

                executors.submit(() -> {
                    herbs.forEach((h) -> {
                        predators.forEach((p) -> {
                            if (h.isAlive() && (isIntersects(p, h) || isIntersects(h, p))) {
                                h.setAlive(false);
                                System.out.printf("predator %s catched herb %s%n", p.getPosition().toString(), h.getPosition().toString());
                            }
                        });

                    });
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
