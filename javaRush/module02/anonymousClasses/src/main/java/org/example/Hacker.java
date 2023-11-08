package org.example;

public class Hacker {
    private String name;

    public Hacker(String name) {
        this.name = name;
    }

    protected String getName() {
        return this.name;
    }

    void hack() {
        new Hacker("DarkHacker") {
            void sayHello(String hackerName) {
                System.out.printf("%s %s %s%n", hackerName, super.getName(), getName());
            }
        }.sayHello(getName());
    }
}
