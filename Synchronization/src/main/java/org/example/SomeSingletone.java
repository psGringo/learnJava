package org.example;

public class SomeSingletone {

    private static SomeSingletone someSingletone;
    private SomeSingletone(){}

    static {
        synchronized (SomeSingletone.class) {
          someSingletone = new SomeSingletone();
        }
    }

    public static SomeSingletone getSomeSingletone(){
        return someSingletone;
    }

}
