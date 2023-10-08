package org.example;

public class AnotherSingletone {

    private static AnotherSingletone anotherSingletone;

    private AnotherSingletone() {
    }

    public static AnotherSingletone getAnotherSingletone() {
        if (anotherSingletone == null) {
            anotherSingletone = new AnotherSingletone();
        }

        return anotherSingletone;
    }
}
