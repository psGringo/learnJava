package org.example.commands;

import org.example.Counter;
import org.example.CounterStateMachine;

public abstract class Command {
    private String name;

    public String getName() {
        return name;
    }

    private final CounterStateMachine stateMachine;

    private final Counter counter;

    public CounterStateMachine getStateMachine() {
        return stateMachine;
    }

    public Counter getCounter() {
        return counter;
    }

    public Command(CounterStateMachine stateMachine, Counter counter) {
        this.stateMachine = stateMachine;
        this.counter = counter;
    }

    public abstract void execute();

}
