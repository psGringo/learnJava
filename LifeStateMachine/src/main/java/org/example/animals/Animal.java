package org.example.animals;


import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import lombok.Getter;
import lombok.Setter;
import org.example.*;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;


public abstract class Animal implements Runnable {
    @Getter
    protected Position position;
    @Getter
    private AnimalType animalType;
    private final Life life;

    @Getter
    @Setter
    private boolean isAlive;

    private TextGraphics textGraphics;


    public abstract String getName();

    private MovementDirection currentDirection;


    public Animal(AnimalType animalType) {
        this.animalType = animalType;
        this.life = Life.getInstance();
        this.currentDirection = MovementDirection.getRandomDirection();
        setStartPosition();
        isAlive = true;
    }

    private void setStartPosition() {
        int maxX = AppProps.getMaxScreenX() - getName().length();
        int maxY = AppProps.getMaxScreenY();
        var positionX = ThreadLocalRandom.current().nextInt(1, maxX - 1);
        var positionY = ThreadLocalRandom.current().nextInt(1, maxY - 1);
        position = new Position(new Coordinates(positionX, positionX + getName().length()), new Coordinates(positionY, positionY + 1));
        try {
            textGraphics = Terminal.getInstance().getLaternaTerminal().newTextGraphics();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getEmptyName() {
        var sb = new StringBuilder();
        for (int i = 0; i < getName().length(); i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    private void moveToNewDirecton() {
        currentDirection = getNewRandomDirection();
        move();
    }

    private MovementDirection getNewRandomDirection() {
        var newDirection = MovementDirection.getRandomDirection();
        // exclude current one
        while (currentDirection.equals(newDirection)) {
            newDirection = MovementDirection.getRandomDirection();
        }

        return newDirection;
    }

    private void updateScreen() {
        try {
            synchronized (Terminal.getInstance().getLaternaScreen()) {
                Terminal.getInstance().getLaternaScreen().refresh(Screen.RefreshType.DELTA);
                synchronized (Terminal.getInstance().getLaternaTerminal()) {
                    Terminal.getInstance().getLaternaTerminal().flush();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    boolean isChangeDirectionNow() {
        return false;// ThreadLocalRandom.current().nextBoolean();
    }

    protected void move() {

        if (!isAlive) {
            textGraphics.putString(position.X.getStart(), position.Y.getStart(), getEmptyName());
            updateScreen();
            return;
        }

        if (currentDirection.equals(MovementDirection.ToTheRight)) {

            if (position.X.getEnd() >= AppProps.getMaxScreenX() - 2 || isChangeDirectionNow()) {
                textGraphics.putString(position.X.getStart(), position.Y.getStart(), getEmptyName());
                textGraphics.putString(position.X.getStart() - 1, position.Y.getStart(), getEmptyName());
                updateScreen();
                moveToNewDirecton();
            } else {
                if (position.X.getStart() > 0 && position.X.getEnd() <= AppProps.getMaxScreenX() - 2) {
                    textGraphics.putString(position.X.getStart() - 1, position.Y.getStart(), getEmptyName());
                    printName(textGraphics);
                }

                if (position.X.getEnd() < AppProps.getMaxScreenX() - 1)
                    position.X.increase();

                updateScreen();
            }


        } else if (currentDirection.equals(MovementDirection.ToTheLeft) || isChangeDirectionNow()) {

            if (position.X.getStart() <= 2) {
                textGraphics.putString(position.X.getStart(), position.Y.getStart(), getEmptyName());
                textGraphics.putString(position.X.getStart() + 1, position.Y.getStart(), getEmptyName());
                updateScreen();
                moveToNewDirecton();
            } else {
                if (position.X.getStart() > 0 && position.X.getEnd() <= AppProps.getMaxScreenX() - 2) {
                    textGraphics.putString(position.X.getStart() + 1, position.Y.getStart(), getEmptyName());
                    printName(textGraphics);
                }

                if (position.X.getStart() > 0)
                    position.X.decrease();

                updateScreen();
            }


        } else if (currentDirection.equals(MovementDirection.ToTheTop) || isChangeDirectionNow()) {

            if (position.Y.getStart() <= 2) {
                textGraphics.putString(position.X.getStart(), position.Y.getStart(), getEmptyName());
                textGraphics.putString(position.X.getStart(), position.Y.getEnd(), getEmptyName());
                updateScreen();
                moveToNewDirecton();
            } else {
                if (position.Y.getStart() > 1 && position.Y.getEnd() < AppProps.getMaxScreenY() - 2) {
                    textGraphics.putString(position.X.getStart(), position.Y.getEnd(), getEmptyName());
                    printName(textGraphics);
                }

                if (position.Y.getStart() > 0)
                    position.Y.decrease();

                updateScreen();
            }

        } else if (currentDirection.equals(MovementDirection.ToTheBottom) || isChangeDirectionNow()) {

            if (position.Y.getEnd() >= AppProps.getMaxScreenY() - 2) {
                textGraphics.putString(position.X.getStart(), position.Y.getStart(), getEmptyName());
                textGraphics.putString(position.X.getStart(), position.Y.getStart() - 1, getEmptyName());
                updateScreen();
                moveToNewDirecton();
            } else if (position.Y.getStart() > 1 && position.Y.getEnd() <= AppProps.getMaxScreenY() - 2) {
                textGraphics.putString(position.X.getStart(), position.Y.getStart() - 1, getEmptyName());
                printName(textGraphics);
                position.Y.increase();
                updateScreen();
            }

        }
    }

    private TextGraphics printName(TextGraphics tg) {
        return tg.putString(position.X.getStart(), position.Y.getStart(), getName());
    }

    @Override
    public void run() {
        move();
    }
}


