package org.example.animals;

import java.util.Random;

public enum MovementDirection {
    ToTheLeft,
    ToTheRight,
    ToTheTop,
    ToTheBottom;


    private static final Random random = new Random();

    public static MovementDirection getRandomDirection() {
        MovementDirection[] directions = values();
        return directions[random.nextInt(directions.length)];
    }
}
